package com.car.park.service.controller.filters;

import com.car.park.service.controller.CommandMapping;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;
import com.car.park.service.model.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.car.park.service.controller.CommandMapping.Commands.*;
import static com.car.park.service.model.UserRole.ADMIN;
import static com.car.park.service.model.UserRole.DRIVER;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

public class AuthenticationFilter implements Filter {

    private static final Map<CommandMapping.Commands, List<UserRole>> PERMISSIONS = new HashMap<CommandMapping.Commands, List<UserRole>>() {{
        put(LOGOUT, asList(ADMIN, DRIVER));
        put(REDIRECT, asList(ADMIN, DRIVER));
        put(CREATE_ROUTE, singletonList(ADMIN));
        put(CREATE_DRIVER_ASSIGNMENT, singletonList(ADMIN));
        put(CREATE_BUS_ASSIGNMENT, singletonList(ADMIN));
        put(CREATE_BUS, singletonList(ADMIN));
        put(GET_ALL_USERS, singletonList(ADMIN));
        put(GET_ALL_BUSES, singletonList(ADMIN));
        put(GET_ALL_ROUTES, singletonList(ADMIN));
        put(GET_USER_DETAILS, singletonList(ADMIN));
        put(GET_ROUTE_DETAILS, singletonList(ADMIN));
        put(GET_UNASSIGNED_DRIVERS, singletonList(ADMIN));
        put(GET_UNASSIGNED_BUSES, singletonList(ADMIN));
        put(EDIT_BUS, singletonList(ADMIN));
        put(EDIT_ROUTE, singletonList(ADMIN));
        put(EDIT_USER, asList(ADMIN, DRIVER));
        put(EDIT_USER_PASSWORD, asList(ADMIN, DRIVER));
        put(EDIT_USER_ASSIGNMENT_ACCEPT, singletonList(DRIVER));
        put(EDIT_USER_ASSIGNMENT_DELETE, singletonList(ADMIN));
        put(EDIT_USER_ROLE, singletonList(ADMIN));
        put(DELETE_ASSIGNMENT, singletonList(ADMIN));
        put(DELETE_ROUTE, singletonList(ADMIN));
        put(DELETE_BUS, singletonList(ADMIN));
        put(DELETE_USER, singletonList(ADMIN));
    }};

    private final UserDao userDao = DaoFactory.getInstance().createUserDao(TransactionManager.getInstance());

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        CommandMapping.Commands command = getCommandFromRequest(request);
        if (commandHasNoRestrictions(command) || userRoleMatchesCommandPermissions(request, command)) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    private CommandMapping.Commands getCommandFromRequest(ServletRequest request) {
        String commandName = request.getParameter("command");
        return commandName == null ? null : CommandMapping.Commands.valueOf(commandName.toUpperCase());
    }

    private boolean commandHasNoRestrictions(CommandMapping.Commands command) {
        return command == null || !PERMISSIONS.containsKey(command);
    }

    private boolean userRoleMatchesCommandPermissions(ServletRequest request, CommandMapping.Commands command) {
        User sessionUser = getSessionUser(request);
        return sessionUser != null && PERMISSIONS.get(command).contains(sessionUser.getAccessRole());
    }

    private User getSessionUser(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        return Optional.ofNullable(session.getAttribute("userId"))
                .map(userId -> userDao.read((long) userId))
                .orElse(null);
    }

    @Override
    public void destroy() {

    }
}
