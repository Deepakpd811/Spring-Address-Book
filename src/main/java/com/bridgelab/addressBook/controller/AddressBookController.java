package com.bridgelab.addressBook.controller;

import com.bridgelab.addressBook.dto.ContactDto;
import com.bridgelab.addressBook.model.Contact;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/addressbook")
public class AddressBookController {

    Map<String, String> response = new HashMap<>();

    @GetMapping
    public List<Contact> getGreet() {

        List<Contact> list = new ArrayList<>();

        Contact con1 = new Contact(10,"deepak","prasad",
                "xyz street","rajpura", "punjab",
                "123484","987648272"
                ,"deepak@gmail.com"
        );Contact con2 = new Contact(10,"deepak","prasad",
                "xyz street","rajpura", "punjab",
                "123484","987648272"
                ,"deepak@gmail.com"
        );
        list.add(con1);
        list.add(con2);
        return list;
    }

    @PostMapping("/create")
    public Map<String, String> postGreet(@RequestBody ContactDto dto) {

        Contact contact = new Contact();
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setZip(dto.getZip());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setEmail(dto.getEmail());


        response.put("Message ", "Contact created");
        return response;
    }



}
