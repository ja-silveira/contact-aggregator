package me.zearthur.endpoints;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import me.zearthur.model.Contact;
import me.zearthur.service.ContactService;

import java.util.List;

@Path("contacts")
public class ContactEndpoint {

    private final ContactService contactService;

    public ContactEndpoint(ContactService contactService) {
        this.contactService = contactService;
    }

    @GET
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }
}
