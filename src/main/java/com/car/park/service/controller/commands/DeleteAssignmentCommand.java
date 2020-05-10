package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.support.TransactionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.GET_ROUTE_DETAILS;
import static java.lang.Long.parseLong;

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
        return getRouteDetailsCommand.execute(request, response);
    }
}
