package com.bridgelab.addressBook.service;

import com.bridgelab.addressBook.dto.ContactDto;
import com.bridgelab.addressBook.model.Contact;
import com.bridgelab.addressBook.exception.AddressBookException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Get all contacts
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    // Create contact
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

    // Get contact by ID
    public Optional<Contact> getContactById(int id) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new AddressBookException("Contact with ID " + id + " not found");
        }
        return contact;
    }

    // Update contact
    public Optional<Contact> updateContact(int id, ContactDto dto) {
        Optional<Contact> contact = contactRepository.findById(id);
        if (contact.isEmpty()) {
            throw new AddressBookException("Contact with ID " + id + " not found for update");
        }

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
    }

    // Delete contact
    public boolean deleteContact(int id) {
        if (!contactRepository.existsById(id)) {
            throw new AddressBookException("Contact with ID " + id + " not found for deletion");
        }

        contactRepository.deleteById(id);
        return true;
    }
}
