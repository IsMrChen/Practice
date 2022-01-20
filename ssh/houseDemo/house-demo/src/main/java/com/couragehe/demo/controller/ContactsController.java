package com.couragehe.demo.controller;

import com.couragehe.demo.entity.Contacts;
import com.couragehe.demo.service.ContactsServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class ContactsController {
    @Autowired
    private ContactsServiceI contactsServiceI;

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String newContact(@RequestBody Contacts contacts) {
        this.contactsServiceI.saveContact(contacts);
        return contacts.toString();
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteContact(int id) {
        this.contactsServiceI.deleteContact(id);
        return "OK";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateContact(@RequestBody Contacts contacts){
        this.contactsServiceI.updateContact(contacts);
        return "OK";
    }

    @RequestMapping(value = "findAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<Contacts> findAllUsers(){
        List<Contacts> contactsList = this.contactsServiceI.findAllUsers();
        return  contactsList;
    }
}
