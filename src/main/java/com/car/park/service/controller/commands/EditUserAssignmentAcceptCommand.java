package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditUserAssignmentAcceptCommand implements Command {

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getUserOfficeCommand;

    public EditUserAssignmentAcceptCommand(
            UserDao userDao, AssignmentDao assignmentDao,
            TransactionManager transactionManager, Command getUserOfficeCommand
    ) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getUserOfficeCommand = getUserOfficeCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long sessionUserId = (long) request.getSession().getAttribute("userId");
        User user = userDao.read(sessionUserId);
        Assignment assignment = assignmentDao.readByDriverId(user.getId());
        assignment.setAcceptedByDriver(true);
        assignmentDao.update(assignment);
        transactionManager.commit();
        request.setAttribute("successMessage", "success.user.assignment.accepted");

        return getUserOfficeCommand.execute(request, response);
    }
}
