package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.GET_USER_OFFICE;

public class GetUserOfficeCommand implements Command {

    private static final String USER_OFFICE_PAGE = "WEB-INF/user-office.jsp";

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;

    public GetUserOfficeCommand(UserDao userDao, AssignmentDao assignmentDao) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long sessionUserId = (long) request.getSession().getAttribute("userId");
        User user = userDao.read(sessionUserId);
        Assignment assignment = assignmentDao.readByDriverId(user.getId());
        user.setAssignment(assignment);

        request.setAttribute("user", user);
        request.setAttribute("command", GET_USER_OFFICE.name());
        return USER_OFFICE_PAGE;
    }
}
