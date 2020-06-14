package com.car.park.service.controller.filters;

import com.car.park.service.controller.CommandMapping;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;
import com.car.park.service.model.UserRole;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.car.park.service.controller.CommandMapping.Commands.*;
import static com.car.park.service.model.UserRole.ROLE_ADMIN;
import static com.car.park.service.model.UserRole.ROLE_DRIVER;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * This filter handles user authentication, it checks whether a user role is acceptable for a certain command.
 * It contains configuration which maps certain command with acceptable user roles.
 * According to this configuration a command will not be executed if user role does not match and login page will be opened instead.
 * In case command has no configuration mapping it is available for any user.
 */
public class AuthenticationFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(AuthenticationFilter.class);

    private static final Map<CommandMapping.Commands, List<UserRole>> PERMISSIONS = new HashMap<CommandMapping.Commands, List<UserRole>>() {{
        put(LOGOUT, asList(ROLE_ADMIN, ROLE_DRIVER));
        put(CREATE_ROUTE, singletonList(ROLE_ADMIN));
        put(CREATE_DRIVER_ASSIGNMENT, singletonList(ROLE_ADMIN));
        put(CREATE_BUS_ASSIGNMENT, singletonList(ROLE_ADMIN));
        put(CREATE_BUS, singletonList(ROLE_ADMIN));
        put(GET_ALL_USERS, singletonList(ROLE_ADMIN));
        put(GET_ALL_BUSES, singletonList(ROLE_ADMIN));
        put(GET_ALL_ROUTES, singletonList(ROLE_ADMIN));
        put(GET_USER_DETAILS, singletonList(ROLE_ADMIN));
        put(GET_ROUTE_DETAILS, singletonList(ROLE_ADMIN));
        put(GET_UNASSIGNED_DRIVERS, singletonList(ROLE_ADMIN));
        put(GET_UNASSIGNED_BUSES, singletonList(ROLE_ADMIN));
        put(EDIT_BUS, singletonList(ROLE_ADMIN));
        put(EDIT_ROUTE, singletonList(ROLE_ADMIN));
        put(EDIT_USER, asList(ROLE_ADMIN, ROLE_DRIVER));
        put(EDIT_USER_PASSWORD, asList(ROLE_ADMIN, ROLE_DRIVER));
        put(EDIT_USER_ASSIGNMENT_ACCEPT, singletonList(ROLE_DRIVER));
        put(EDIT_USER_ASSIGNMENT_DELETE, asList(ROLE_ADMIN, ROLE_DRIVER));
        put(EDIT_USER_ROLE, singletonList(ROLE_ADMIN));
        put(DELETE_ASSIGNMENT, singletonList(ROLE_ADMIN));
        put(DELETE_ROUTE, singletonList(ROLE_ADMIN));
        put(DELETE_BUS, singletonList(ROLE_ADMIN));
        put(DELETE_USER, singletonList(ROLE_ADMIN));
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
            LOG.warn(format("Access denied, trying to execute command '%s'", request.getParameter("command")));
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
