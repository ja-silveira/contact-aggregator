package me.zearthur.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;
import me.zearthur.model.ContactExternalApi;
import me.zearthur.model.Contact;
import me.zearthur.model.ExternalApiSource;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.LinkedList;
import java.util.List;

@RequestScoped
public class ContactService {

    ExternalContactApiService externalContactApiService;

    public ContactService(@RestClient ExternalContactApiService externalContactApiService) {
        this.externalContactApiService = externalContactApiService;
    }

    public List<Contact> getAllContacts() {
        List<ContactExternalApi> contactsFound = new LinkedList<>();
        int currentPage = 1;
        int totalItems = -1;

        while (contactsFound.size() < totalItems || totalItems == -1) {
            Response externalApiResponse = externalContactApiService.getPage(currentPage, 20);
            contactsFound.addAll(externalApiResponse.readEntity(new GenericType<List<ContactExternalApi>>() {}));
            totalItems = Integer.parseInt(String.valueOf(externalApiResponse.getHeaders().get("total-count").get(0)));
            currentPage++;
        }

        return contactsFound.stream().map(contactExternalApi -> new Contact(contactExternalApi.id())
                .setCreatedAt(contactExternalApi.createdAt())
                .setUpdatedAt(contactExternalApi.updatedAt())
                .setSource(ExternalApiSource.KENECT_LABS)
                .setEmail(contactExternalApi.email())
                .setName(contactExternalApi.name())).toList();
    }

}
