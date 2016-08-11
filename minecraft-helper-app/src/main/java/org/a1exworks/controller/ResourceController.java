package org.a1exworks.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.a1exworks.SkinService;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class ResourceController extends ResourceHandler {
    private SkinService skinService;

    public ResourceController(String root) {
        super();
        skinService = new SkinService(root);
        setResourceBase(root);
    }

    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        super.handle(target, baseRequest, request, response);
        if (skinService.hasResource(target)) {
            baseRequest.setHandled(true);
        }
    }
}
