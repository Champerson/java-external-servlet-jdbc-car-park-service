package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This command is responsible for logging out from session
 */
public class LogoutCommand implements Command {

    private static final String INDEX_PAGE = "index.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return INDEX_PAGE;
    }
}
