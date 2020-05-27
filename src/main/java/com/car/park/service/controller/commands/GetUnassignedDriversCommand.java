package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.car.park.service.model.UserRole.DRIVER;
import static java.util.stream.Collectors.toList;

public class GetUnassignedDriversCommand implements Command {

    private static final String UNASSIGNED_DRIVERS_PAGE = "WEB-INF/unassigned-drivers.jsp";

    private final UserDao userDao;

    public GetUnassignedDriversCommand(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("routeId", request.getParameter("routeId"));
        request.setAttribute("assignmentId", request.getParameter("assignmentId"));
        request.setAttribute("users", getDriversAvailableForAssignment());
        return UNASSIGNED_DRIVERS_PAGE;
    }

    private List<User> getDriversAvailableForAssignment() {
        return userDao.readAllWithoutAssignment().stream()
                .filter(user -> DRIVER.equals(user.getAccessRole()))
                .collect(toList());
    }
}
