package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

/**
 * This command deletes Route and it's Assignments if exist
 */
public class DeleteRouteCommand implements Command {

    private final RouteDao routeDao;
    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getAllRoutesCommand;

    public DeleteRouteCommand(
            RouteDao routeDao,
            AssignmentDao assignmentDao,
            TransactionManager transactionManager,
            Command getAllRoutesCommand
    ) {
        this.routeDao = routeDao;
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getAllRoutesCommand = getAllRoutesCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long routeId = parseLong(request.getParameter("routeId"));
        for (Assignment assignment : assignmentDao.readByRouteId(routeId)) {
            assignmentDao.delete(assignment.getId());
        }
        routeDao.delete(routeId);
        transactionManager.commit();
        request.setAttribute("successMessage", "success.route.deleted");
        return getAllRoutesCommand.execute(request, response);
    }
}
