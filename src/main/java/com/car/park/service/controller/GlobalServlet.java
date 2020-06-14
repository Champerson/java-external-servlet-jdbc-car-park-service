package com.car.park.service.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This servlet handles all POST requests.
 * It executes an appropriate command according to 'command' parameter passed within a request
 * and forwards to a page which is a result of the command execution.
 */
public class GlobalServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/htm");
        String commandName = request.getParameter("command").toUpperCase();
        String page = getCommandByName(commandName).execute(request, response);
        request.setAttribute("command", commandName);
        tryToForward(page, request, response);
    }

    private Command getCommandByName(String commandName) {
        return CommandMapping.Commands.valueOf(commandName).command;
    }

    private void tryToForward(String page, HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher(page).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
