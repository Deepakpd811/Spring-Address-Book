package com.bridgelab.addressBook.service;


import com.bridgelab.addressBook.dto.ContactDto;
import com.bridgelab.addressBook.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // get all contact list
    public List getAllContact() {
        return contactRepository.findAll();
    }

    // create contact
    public Contact createContact(ContactDto dto) {
        Contact contact = new Contact();

        contact.setFirstName(dto.getFirstName());
        contact.setLastName(dto.getLastName());
        contact.setAddress(dto.getAddress());
        contact.setCity(dto.getCity());
        contact.setState(dto.getState());
        contact.setZip(dto.getZip());
        contact.setPhoneNumber(dto.getPhoneNumber());
        contact.setEmail(dto.getEmail());

        contactRepository.save(contact);
        return contact;
    }


    // Get contact by id
    public Optional<Contact> getContactById(int id) {
        return contactRepository.findById(id);
    }

    // Update contact
    public Optional<Contact> updateContact(int id, ContactDto dto) {

        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {

            Contact updateContact = contact.get();

            updateContact.setFirstName(dto.getFirstName());
            updateContact.setLastName(dto.getLastName());
            updateContact.setAddress(dto.getAddress());
            updateContact.setCity(dto.getCity());
            updateContact.setState(dto.getState());
            updateContact.setZip(dto.getZip());
            updateContact.setPhoneNumber(dto.getPhoneNumber());
            updateContact.setEmail(dto.getEmail());

            return Optional.of(contactRepository.save(updateContact));
        } else {
            return contact;
        }

    }

    // delete contact
    public boolean deleteContact(int id) {
        if (contactRepository.existsById(id)) {
            contactRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }


}
