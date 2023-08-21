package org.acme;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GreetingItem hello() {
        return new GreetingItem("RESTEasy Reactive",
                "You",
                "Hello!");
    }
}
