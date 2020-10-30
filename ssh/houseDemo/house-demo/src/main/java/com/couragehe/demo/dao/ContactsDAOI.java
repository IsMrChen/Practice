package com.couragehe.demo.dao;


import com.couragehe.demo.entity.Contacts;

import java.util.List;

public interface ContactsDAOI {
    public List<Contacts> findAllContacts();

    public void saveContact(Contacts contacts);

    public Contacts findById(int id);

    public void deleteContact(int id);

    public void updateContact(Contacts contacts);

    public List<Contacts> findByContactName(String name);
}
