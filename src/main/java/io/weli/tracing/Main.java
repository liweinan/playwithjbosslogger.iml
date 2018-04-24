package io.weli.tracing;

import io.undertow.servlet.api.DeploymentInfo;
import org.jboss.logmanager.LogManager;
import org.jboss.resteasy.plugins.server.servlet.ResteasyContextParameters;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class Main {
    public static void main(String[] args) throws IOException {

        UndertowJaxrsServer server;
        Client client;
        javax.servlet.ServletException exception;
        System.setProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager");
        LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResourceAsStream("logging.jboss.properties"));

        server = new UndertowJaxrsServer().start();
        ResteasyDeployment deployment = new ResteasyDeployment();

        deployment.setApplicationClass(TracingApp.class.getName());

        DeploymentInfo di = server.undertowDeployment(deployment);
        di.setClassLoader(TracingApp.class.getClassLoader());
        di.setContextPath("");
        di.setDeploymentName("Resteasy");
        di.getServlets().get("ResteasyServlet").addInitParam(ResteasyContextParameters.RESTEASY_TRACING_TYPE, ResteasyContextParameters.RESTEASY_TRACING_TYPE_ALL)
                .addInitParam(ResteasyContextParameters.RESTEASY_TRACING_THRESHOLD, ResteasyContextParameters.RESTEASY_TRACING_LEVEL_VERBOSE);
        server.deploy(di);

        client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8081/type");
        assertEquals(ResteasyContextParameters.RESTEASY_TRACING_TYPE_ALL, target.request().get(String.class));

        target = client.target("http://localhost:8081/level");
        assertEquals(ResteasyContextParameters.RESTEASY_TRACING_LEVEL_VERBOSE, target.request().get(String.class));

        target = client.target("http://localhost:8081/logger");
        target.request().get();

        client.close();
        server.stop();
    }
}