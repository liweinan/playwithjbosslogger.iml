package io.weli.tracing;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.tracing.RESTEasyTracingLogger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;
import java.io.IOException;

@Path("/")
public class TracingConfigResource extends Application {

    @GET
    @Path("/type")
    public String type() {
        return ResteasyProviderFactory.getTracingType().toString();
    }

    @GET
    @Path("/level")
    public String level() {
        return ResteasyProviderFactory.getTracingThreshold().toString();
    }

    @GET
    @Path("/logger")
    public String logger() throws NoSuchMethodException {
        RESTEasyTracingLogger logger = ResteasyProviderFactory.getTracingLogger();
//        logger.log(RESTEasyServerTracingEvent.METHOD_INVOKE, TracingConfigResource.class.toString(),
//                "logger()");
        return logger.toString();
    }

    @GET
    @Path("/io")
    public String io() throws MyException {
        throw new MyException();
    }

}
