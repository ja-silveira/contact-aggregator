package me.zearthur.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "external-contact-api")
public interface ExternalContactApiService {

    @GET
    @ClientHeaderParam(name = "Authorization", value = "${me.zearthur.external-contact-api.token}")
    Response getPage(@QueryParam("page") Integer page, @QueryParam("pageSize") Integer size);

}
