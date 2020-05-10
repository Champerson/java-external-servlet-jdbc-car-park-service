package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return request.getParameter("pageToRedirect");
    }
}
