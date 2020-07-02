package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This command is responsible for redirect to pages indicated in request
 */
public class RedirectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        cleanUpValidationResult(request);
        return request.getParameter("pageToRedirect");
    }

    private void cleanUpValidationResult(HttpServletRequest request) {
        if (request.getAttribute("locale") == null) {
            request.getSession().removeAttribute("validationResult");
        }
    }
}
