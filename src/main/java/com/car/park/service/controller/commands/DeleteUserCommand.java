package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

/**
 * This command deletes User and updates it's Assignment if exist removing User from it
 */
public class DeleteUserCommand implements Command {

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getAllUsersCommand;

    public DeleteUserCommand(
            UserDao userDao, AssignmentDao assignmentDao,
            TransactionManager transactionManager, Command getAllUsersCommand
    ) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getAllUsersCommand = getAllUsersCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = parseLong(request.getParameter("userId"));
        Assignment userAssignment = assignmentDao.readByDriverId(userId);
        if (userAssignment != null) {
            userAssignment.setDriver(null);
            userAssignment.setAcceptedByDriver(false);
            assignmentDao.update(userAssignment);
        }
        userDao.delete(userId);
        transactionManager.commit();
        request.setAttribute("successMessage", "success.user.deleted");

        return getAllUsersCommand.execute(request, response);
    }
}
