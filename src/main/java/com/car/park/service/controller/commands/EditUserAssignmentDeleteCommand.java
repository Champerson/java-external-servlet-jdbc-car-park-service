package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.CommandMapping;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;


public class EditUserAssignmentDeleteCommand implements Command {

    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;

    public EditUserAssignmentDeleteCommand(AssignmentDao assignmentDao, TransactionManager transactionManager) {
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long assignmentId = parseLong(request.getParameter("assignmentId"));
        Assignment assignment = assignmentDao.read(assignmentId);
        assignment.setDriver(null);
        assignment.setAcceptedByDriver(false);
        assignmentDao.update(assignment);
        transactionManager.commit();
        request.setAttribute("successMessage", "success.user.assignment.declined");

        return getNextCommand(request).execute(request, response);
    }

    private Command getNextCommand(HttpServletRequest request) {
        String nextCommand = request.getParameter("nextCommand").toUpperCase();
        return CommandMapping.Commands.valueOf(nextCommand).command();
    }
}
