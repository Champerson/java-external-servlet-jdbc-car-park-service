package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.support.PasswordEncoder;
import com.car.park.service.controller.validation.UserValidationDto;
import com.car.park.service.controller.validation.UserValidationResultBuilder;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.User;
import com.car.park.service.model.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.car.park.service.model.UserRole.ROLE_ADMIN;
import static com.car.park.service.model.UserRole.ROLE_DRIVER;

/**
 * This command is responsible for the authorization process. It handles incoming credentials, obtains User from data base
 * and redirects to the appropriate page according to the UserRole.
 * In case user is ADMIN - redirects to admin-menu-page.jsp
 * In case user is DRIVER - executes GetUserOfficeCommand
 * Other cases - redirects to login page
 */
public class AuthorizationCommand implements Command {

    private static final String INDEX_PAGE = "index.jsp";
    private static final String ADMIN_MENU_PAGE = "WEB-INF/jsp/admin-menu-page.jsp";

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

        if (credentialsValidationFailed(login, password)) {
            request.setAttribute("errorMessage", "error.credentials.invalid");
            return INDEX_PAGE;
        }
        User user = userDao.read(login);
        if (!userMatchesCredentials(user, password)) {
            request.setAttribute("errorMessage", "error.credentials.invalid");
            return INDEX_PAGE;
        }
        startSessionForUser(request, user);
        return determinePageByUserAccessRole(user.getAccessRole(), request, response);
    }

    private boolean credentialsValidationFailed(String inputLogin, String inputPassword) {
        UserValidationDto userValidationDto = new UserValidationResultBuilder()
                .validateLogin(inputLogin)
                .validatePassword(inputPassword)
                .errors();
        return userValidationDto.validationFailed();
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
        if (ROLE_DRIVER.equals(role)) {
            return getUserOfficeCommand.execute(request, response);
        } else if (ROLE_ADMIN.equals(role)) {
            return ADMIN_MENU_PAGE;
        } else {
            return INDEX_PAGE;
        }
    }
}
