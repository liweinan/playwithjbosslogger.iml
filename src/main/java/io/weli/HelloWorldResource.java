package io.weli;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/hello")
public class HelloWorldResource {
    @GET
    public String sayHello() {
        return "Hello, world!";
    }
}
