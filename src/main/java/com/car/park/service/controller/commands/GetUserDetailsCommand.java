package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

public class GetUserDetailsCommand implements Command {

    private static final String USER_INFO_PAGE = "WEB-INF/user-details.jsp";

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;

    public GetUserDetailsCommand(UserDao userDao, AssignmentDao assignmentDao) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = parseLong(request.getParameter("userId"));

        User user = userDao.read(userId);
        Assignment assignment = assignmentDao.readByDriverId(user.getId());
        user.setAssignment(assignment);

        request.setAttribute("user", user);
        request.setAttribute("assignment", assignment);
        return USER_INFO_PAGE;
    }
}
