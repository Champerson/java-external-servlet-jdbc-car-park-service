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
 * This command updates an existing Assignment to connect User with Bus and Route.
 */
public class CreateAssignmentForDriverCommand implements Command {

    private final UserDao userDao;
    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getRouteDetailsCommand;

    public CreateAssignmentForDriverCommand(
            UserDao userDao, AssignmentDao assignmentDao,
            TransactionManager transactionManager, Command getRouteDetailsCommand
    ) {
        this.userDao = userDao;
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getRouteDetailsCommand = getRouteDetailsCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long userId = parseLong(request.getParameter("userId"));
        long assignmentId = parseLong(request.getParameter("assignmentId"));

        Assignment assignment = assignmentDao.read(assignmentId);
        assignment.setDriver(userDao.read(userId));
        assignmentDao.update(assignment);
        transactionManager.commit();

        return getRouteDetailsCommand.execute(request, response);
    }
}
