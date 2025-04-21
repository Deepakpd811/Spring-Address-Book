package com.bridgelab.addressBook.service;


import com.bridgelab.addressBook.dto.ContactDto;
import com.bridgelab.addressBook.model.Contact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    List<Contact> contactList = new ArrayList<>();

    // get all contect list
    public List<Contact> getAllContact(){
        return contactList;
    }

    // create contact
    public Contact createContact(ContactDto dto) {
        Contact contact = new Contact();
        contact.setId(contactList.size() + 1); // Simple in-memory ID
        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setZip(dto.getZip());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setEmail(dto.getEmail());

        contact.setId(contactList.size() + 1);
        contactList.add(contact);
        return contact;
    }

}
