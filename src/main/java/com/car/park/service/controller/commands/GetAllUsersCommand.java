package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllUsersCommand implements Command {

    private static final String ALL_USERS_PAGE = "WEB-INF/all-users.jsp";

    private final UserDao userDao;

    public GetAllUsersCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("users", userDao.readAll());
        return ALL_USERS_PAGE;
    }
}
