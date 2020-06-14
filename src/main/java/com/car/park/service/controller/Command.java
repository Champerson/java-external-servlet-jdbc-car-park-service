package com.car.park.service.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands;
import static com.car.park.service.controller.CommandMapping.Commands.valueOf;

public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response);

    default void cleanUpValidationResult(HttpServletRequest request, Commands expectedCommand) {
        String initialCommand = request.getParameter("command").toUpperCase();
        boolean localeSwitching = request.getParameter("locale") != null;
        boolean entityModification = expectedCommand.equals(valueOf(initialCommand));

        if (!localeSwitching && !entityModification) {
            request.getSession().removeAttribute("validationResult");
        }
    }
}
