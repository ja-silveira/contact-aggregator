package me.zearthur;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import me.zearthur.model.Contact;
import me.zearthur.model.ContactExternalApi;
import me.zearthur.service.ContactService;
import me.zearthur.service.ExternalContactApiService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@QuarkusTest
class ContactServiceTest {

    private static final int CONTACT_AMOUNT = 21;

    @InjectMock
    @RestClient
    ExternalContactApiService externalContactApiService;

    @Inject
    ContactService contactService;

    @BeforeEach
    public void setup() {
        List<ContactExternalApi> contacts = new ArrayList<>();

        for(long i = 0; i < CONTACT_AMOUNT; i++) {
            contacts.add(
                    new ContactExternalApi(i,
                            "Test contact"+i,
                            "test-contact-" + i + "@gmail.com",
                            LocalDateTime.now(),
                            LocalDateTime.now()
                    ));
        }

        Response page1Response = Response.ok(contacts.subList(0,20)).header("total-count", contacts.size()).build();
        Response page2Response = Response.ok(contacts.subList(20,21)).header("total-count", contacts.size()).build();
        Mockito.when(externalContactApiService.getPage(1, 20)).thenReturn(page1Response);
        Mockito.when(externalContactApiService.getPage(2, 20)).thenReturn(page2Response);
    }

    @Test
    void testPageWrap() {
        List<Contact> contacts = contactService.getAllContacts();
        Assertions.assertEquals(CONTACT_AMOUNT, contacts.size());
    }

    @Test
    void testResponseSortedByPages() {
        List<Contact> contacts = contactService.getAllContacts();
        Assertions.assertEquals(0, contacts.getFirst().getId());
        Assertions.assertEquals(20, contacts.get(20).getId());
    }
}
