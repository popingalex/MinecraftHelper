package org.a1exworks;

import org.a1exworks.controller.ResourceController;
import org.a1exworks.controller.StatusController;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class HelperApp {
    public static void main(String[] args) {
        ServletHandler servletHandler = new ServletHandler();
//        servletHandler.addServletWithMapping(new ServletHolder(new StatusController("60.205.111.65", 25565)), "/status");
        servletHandler.addServletWithMapping(new ServletHolder(new StatusController("localhost", 25565)), "/status");
        
        HandlerList handlers = new HandlerList();
        handlers.addHandler(new ResourceController("mcresource"));
        handlers.addHandler(servletHandler);
        
        Server server = new Server(26000);
        server.setHandler(handlers);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
