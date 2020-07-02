package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.support.TransactionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

/**
 * This command deletes Assignment
 */
public class DeleteAssignmentCommand implements Command {

    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getRouteDetailsCommand;

    public DeleteAssignmentCommand(AssignmentDao assignmentDao, TransactionManager transactionManager, Command getRouteDetailsCommand) {
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getRouteDetailsCommand = getRouteDetailsCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long assignmentId = parseLong(request.getParameter("assignmentId"));
        assignmentDao.delete(assignmentId);
        transactionManager.commit();
        request.setAttribute("successMessage", "success.route.assignment.deleted");
        return getRouteDetailsCommand.execute(request, response);
    }
}
