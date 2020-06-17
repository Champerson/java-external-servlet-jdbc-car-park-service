package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.*;

public class GetUserOfficeCommand implements Command {

    private static final String USER_OFFICE_PAGE = "WEB-INF/jsp/user-office-page.jsp";

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;

    public GetUserOfficeCommand(UserDao userDao, AssignmentDao assignmentDao) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        cleanUpUserValidationResult(request);
        long sessionUserId = (long) request.getSession().getAttribute("userId");
        User user = userDao.read(sessionUserId);
        Assignment assignment = assignmentDao.readByDriverId(user.getId());
        user.setAssignment(assignment);

        request.setAttribute("user", user);
        request.setAttribute("command", GET_USER_OFFICE.name());
        return USER_OFFICE_PAGE;
    }

    private void cleanUpUserValidationResult(HttpServletRequest request) {
        String initialCommand = request.getParameter("command").toUpperCase();
        boolean localeSwitching = request.getParameter("locale") != null;
        boolean userDetailsModification = EDIT_USER.equals(valueOf(initialCommand));
        boolean userPasswordModification = EDIT_USER_PASSWORD.equals(valueOf(initialCommand));

        if (!localeSwitching && !userDetailsModification && !userPasswordModification) {
            request.getSession().removeAttribute("validationResult");
        }
    }
}
