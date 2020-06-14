package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllUsersCommand implements Command {

    private static final String ALL_USERS_PAGE = "WEB-INF/jsp/admin-user-all-page.jsp";

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;

    public GetAllUsersCommand(UserDao userDao, AssignmentDao assignmentDao) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<User> users = userDao.readAll();
        for (User user : users) {
            user.setAssignment(assignmentDao.readByDriverId(user.getId()));
        }
        request.setAttribute("users", users);
        return ALL_USERS_PAGE;
    }
}
