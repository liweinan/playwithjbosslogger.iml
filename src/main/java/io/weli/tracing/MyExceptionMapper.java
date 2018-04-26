package io.weli.tracing;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class MyExceptionMapper implements ExceptionMapper<MyException> {
    @Override
    public Response toResponse(MyException e) {
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
