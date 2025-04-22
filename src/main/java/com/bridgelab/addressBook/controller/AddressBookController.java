package com.bridgelab.addressBook.controller;

import com.bridgelab.addressBook.dto.ApiResponse;
import com.bridgelab.addressBook.dto.ContactDto;
import com.bridgelab.addressBook.model.Contact;
import com.bridgelab.addressBook.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    private final ContactService contactService;

    @Autowired
    public AddressBookController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Get all contacts
    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getAllContacts() {
        log.info("Fetching all contacts");
        List<Contact> contacts = contactService.getAllContact();
        if (contacts.isEmpty()) {
            return buildErrorResponse("No contacts found", HttpStatus.NOT_FOUND);
        }
        return buildSuccessResponse("Contacts retrieved successfully", contacts, HttpStatus.OK);
    }

    // Create a new contact
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Contact>> createContact(@Valid @RequestBody ContactDto dto) {
        log.info("Creating a new contact");
        Contact createdContact = contactService.createContact(dto);
        return buildSuccessResponse("Contact created successfully", createdContact, HttpStatus.CREATED);
    }

    // Get a contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Contact>> getContactById(@PathVariable int id) {
        log.info("Fetching contact by ID: {}", id);
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isPresent()) {
            return buildSuccessResponse("Contact retrieved successfully", contact.get(), HttpStatus.OK);
        } else {
            return buildErrorResponse("Contact with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    // Update a contact by ID
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Contact>> updateContact(@PathVariable int id, @Valid @RequestBody ContactDto dto) {
        log.info("Updating contact with ID: {}", id);
        Optional<Contact> updatedContact = contactService.updateContact(id, dto);
        if (updatedContact.isPresent()) {
            return buildSuccessResponse("Contact updated successfully", updatedContact.get(), HttpStatus.OK);
        } else {
            return buildErrorResponse("Contact with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    // Delete a contact by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContact(@PathVariable int id) {
        log.info("Deleting contact with ID: {}", id);
        boolean isDeleted = contactService.deleteContact(id);
        if (isDeleted) {
            return buildSuccessResponse("Contact deleted successfully", null, HttpStatus.OK);
        } else {
            return buildErrorResponse("Contact with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    // Helper method to create success response
    private <T> ResponseEntity<ApiResponse<T>> buildSuccessResponse(String message, T data, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>("success", message, data);
        return new ResponseEntity<>(response, status);
    }

    // Helper method to create error response
    private <T> ResponseEntity<ApiResponse<T>> buildErrorResponse(String message, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>("error", message, null);
        return new ResponseEntity<>(response, status);
    }
}
