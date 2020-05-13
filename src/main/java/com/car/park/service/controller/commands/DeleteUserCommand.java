package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

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
            assignmentDao.delete(userAssignment.getId());
        }
        userDao.delete(userId);
        transactionManager.commit();
        return getAllUsersCommand.execute(request, response);
    }
}
