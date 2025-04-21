package com.bridgelab.addressBook.controller;

import com.bridgelab.addressBook.dto.ApiResponse;
import com.bridgelab.addressBook.dto.ContactDto;
import com.bridgelab.addressBook.model.Contact;
import com.bridgelab.addressBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {


    private ContactService contactService;

    @Autowired
    public AddressBookController(ContactService contactService) {
        this.contactService = contactService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Contact>>> getGreetAll() {
        try {
            List<Contact> list = contactService.getAllContact();

            if (list.isEmpty()) {
                ApiResponse<List<Contact>> api = new ApiResponse<>(
                        "error",
                        "List is empty",
                        list
                );
                return new ResponseEntity<>(api, HttpStatus.NOT_FOUND);
            }

            ApiResponse<List<Contact>> api = new ApiResponse<>(
                    "success",
                    "Contacts retrieved successfully",
                    list
            );
            return new ResponseEntity<>(api, HttpStatus.OK);

        } catch (Exception e) {
            ApiResponse<List<Contact>> api = new ApiResponse<>(
                    "error",
                    "Internal Server Error",
                    null
            );
            return new ResponseEntity<>(api, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Contact>> postGreet(@RequestBody ContactDto dto) {

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


}
