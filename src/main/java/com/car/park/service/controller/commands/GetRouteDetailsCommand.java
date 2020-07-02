package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.model.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.EDIT_ROUTE;
import static java.lang.Long.parseLong;

/**
 * This command shows Route info on ROUTE_INFO_PAGE
 */
public class GetRouteDetailsCommand implements Command {

    private static final String ROUTE_INFO_PAGE = "WEB-INF/jsp/admin-route-details-page.jsp";

    private final RouteDao routeDao;
    private final AssignmentDao assignmentDao;

    public GetRouteDetailsCommand(RouteDao routeDao, AssignmentDao assignmentDao) {
        this.routeDao = routeDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        cleanUpValidationResult(request, EDIT_ROUTE);
        long routeId = parseLong(request.getParameter("routeId"));
        Route route = routeDao.read(routeId);
        route.setAssignments(assignmentDao.readByRouteId(route.getId()));

        request.setAttribute("route", route);
        return ROUTE_INFO_PAGE;
    }
}
