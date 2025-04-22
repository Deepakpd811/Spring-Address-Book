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

import java.util.*;

@Slf4j
@RestController
@RequestMapping("/addressbook")
public class AddressBookController {


    private ContactService contactService;

    @Autowired
    public AddressBookController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Get all contact
    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getGreetAll() {
        log.info("Fetching all Contacts");
        try {
            List<Contact> list = contactService.getAllContact();

            if (list.isEmpty()) {
                log.warn("No Contact found");
                ApiResponse<List<Contact>> api = new ApiResponse<>(
                        "error",
                        "List is empty",
                        list
                );
                return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
            }

            log.info("Retriveing all contact successfully count:{}", list.size());

            ApiResponse<List<Contact>> api = new ApiResponse<>(
                    "success",
                    "Contacts retrieved successfully",
                    list
            );
            return new ResponseEntity<>(api, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Error while fetching ");
            ApiResponse<List<Contact>> api = new ApiResponse<>(
                    "error",
                    "Internal Server Error",
                    null
            );
            return new ResponseEntity<>(api, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // create a contact
    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Contact>> postContact(@Valid @RequestBody ContactDto dto) {

        try {
            // Create the contact via service
            Contact createdContact = contactService.createContact(dto);

            // Build success response
            ApiResponse<Contact> apiResponse = new ApiResponse<>(
                    "success",
                    "Contact created successfully",
                    createdContact
            );

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            ApiResponse<Contact> api = new ApiResponse<>(
                    "error",
                    "Internal Server Error",
                    null
            );
            return new ResponseEntity<>(api, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    // Get a contact by id
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Contact>> getContactById(@PathVariable int id) {

        try {
            Optional<Contact> list = contactService.getContactById(id);

            if (list.isEmpty()) {
                ApiResponse<Contact> api = new ApiResponse<>(
                        "error",
                        "List is empty",
                        list.get()
                );
                return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Contact> api = new ApiResponse<>(
                    "success",
                    "Contacts retrieved successfully",
                    list.get()
            );
            return new ResponseEntity<>(api, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Contact> api = new ApiResponse<>(
                    "error",
                    "Internal Server Error",
                    null
            );
            return new ResponseEntity<>(api, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // Update a contact by id
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Contact>> updateContactById(@PathVariable int id,@Valid @RequestBody ContactDto dto) {

        try {
            Optional<Contact> list = contactService.updateContact(id, dto);

            if (list.isEmpty()) {
                ApiResponse<Contact> api = new ApiResponse<>(
                        "error",
                        "Id not found",
                        null
                );
                return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
            }

            ApiResponse<Contact> api = new ApiResponse<>(
                    "success",
                    "Contacts Update successfully",
                    list.get()
            );
            return new ResponseEntity<>(api, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<Contact> api = new ApiResponse<>(
                    "error",
                    "Internal Server Error",
                    null
            );
            return new ResponseEntity<>(api, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    //Delete contact by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteContactById(@PathVariable int id) {
        try {
            boolean isDeleted = contactService.deleteContact(id);

            if (isDeleted) {
                ApiResponse<Void> api = new ApiResponse<>(
                        "success",
                        "Contact deleted successfully",
                        null
                );
                return new ResponseEntity<>(api, HttpStatus.OK);
            } else {
                ApiResponse<Void> api = new ApiResponse<>(
                        "error",
                        "Contact with given ID not found",
                        null
                );
                return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            ApiResponse<Void> api = new ApiResponse<>(
                    "error",
                    "Internal Server Error",
                    null
            );
            return new ResponseEntity<>(api, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
