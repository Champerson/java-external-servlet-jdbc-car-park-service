package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;
import static java.time.LocalDateTime.now;

/**
 * This command creates a new Assignment to connect Bus with Route.
 */
public class CreateAssignmentForBusCommand implements Command {

    private final RouteDao routeDao;
    private final BusDao busDao;
    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getRouteDetailsCommand;

    public CreateAssignmentForBusCommand(
            RouteDao routeDao, BusDao busDao, AssignmentDao assignmentDao,
            TransactionManager transactionManager, Command getRouteDetailsCommand
    ) {
        this.routeDao = routeDao;
        this.busDao = busDao;
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getRouteDetailsCommand = getRouteDetailsCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long routeId = parseLong(request.getParameter("routeId"));
        long busId = parseLong(request.getParameter("busId"));

        Assignment assignment = new Assignment();
        assignment.setAcceptedByDriver(false);
        assignment.setStartDate(now().toLocalDate());
        assignment.setCreationTime(now());
        assignment.setBus(busDao.read(busId));
        assignment.setRoute(routeDao.read(routeId));
        assignmentDao.create(assignment);
        transactionManager.commit();
        request.setAttribute("successMessage", "success.route.assignment.created");

        return getRouteDetailsCommand.execute(request, response);
    }
}
