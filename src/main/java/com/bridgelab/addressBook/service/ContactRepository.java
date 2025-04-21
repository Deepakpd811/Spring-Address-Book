package com.bridgelab.addressBook.service;

import com.bridgelab.addressBook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
}
