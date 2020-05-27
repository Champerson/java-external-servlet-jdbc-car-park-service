package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class EditUserAssignmentDeleteCommand implements Command {

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getUserDetailsCommand;

    public EditUserAssignmentDeleteCommand(
            UserDao userDao, AssignmentDao assignmentDao,
            TransactionManager transactionManager, Command getUserDetailsCommand
    ) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getUserDetailsCommand = getUserDetailsCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = Long.parseLong(request.getParameter("userId"));
        User user = userDao.read(userId);
        Assignment assignment = assignmentDao.readByDriverId(user.getId());
        assignment.setDriver(null);
        assignment.setAcceptedByDriver(false);
        assignmentDao.update(assignment);
        transactionManager.commit();

        return getUserDetailsCommand.execute(request, response);
    }
}
