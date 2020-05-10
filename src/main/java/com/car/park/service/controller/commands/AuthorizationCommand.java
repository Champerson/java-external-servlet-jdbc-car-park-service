package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.support.PasswordEncoder;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.User;
import com.car.park.service.model.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.car.park.service.model.UserRole.ADMIN;
import static com.car.park.service.model.UserRole.DRIVER;

public class AuthorizationCommand implements Command {

    private static final String INDEX_PAGE = "index.jsp";
    private static final String ADMIN_MENU_PAGE = "WEB-INF/admin-menu.jsp";

    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final Command getUserOfficeCommand;

    public AuthorizationCommand(UserDao userDao, PasswordEncoder passwordEncoder, Command getUserOfficeCommand) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.getUserOfficeCommand = getUserOfficeCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userDao.read(login);
        if (!userMatchesCredentials(user, password)) {
            return INDEX_PAGE;
        }
        startSessionForUser(request, user);
        return determinePageByUserAccessRole(user.getAccessRole(), request, response);
    }

    private boolean userMatchesCredentials(User user, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        return user != null && user.getPassword().equals(encodedPassword);
    }

    private void startSessionForUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("userId", user.getId());
    }

    private String determinePageByUserAccessRole(UserRole role, HttpServletRequest request, HttpServletResponse response) {
        if (DRIVER.equals(role)) {
            return getUserOfficeCommand.execute(request, response);
        } else if (ADMIN.equals(role)) {
            return ADMIN_MENU_PAGE;
        } else {
            return INDEX_PAGE;
        }
    }
}
