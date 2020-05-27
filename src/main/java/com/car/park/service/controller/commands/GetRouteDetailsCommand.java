package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.model.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

public class GetRouteDetailsCommand implements Command {

    private static final String ROUTE_INFO_PAGE = "WEB-INF/route-details.jsp";

    private final RouteDao routeDao;
    private final AssignmentDao assignmentDao;

    public GetRouteDetailsCommand(RouteDao routeDao, AssignmentDao assignmentDao) {
        this.routeDao = routeDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String routeIdRequest = request.getParameter("routeId");

        if (routeIdRequest != null && !routeIdRequest.isEmpty()) {
            long routeId = parseLong(routeIdRequest);

            Route route = routeDao.read(routeId);
            route.setAssignments(assignmentDao.readByRouteId(route.getId()));

            request.setAttribute("route", route);
            request.getSession().setAttribute("routeId", routeId);
        } else {
            long routeId = (long) request.getSession().getAttribute("routeId");

            Route route = routeDao.read(routeId);
            route.setAssignments(assignmentDao.readByRouteId(route.getId()));

            request.setAttribute("route", route);
        }
        return ROUTE_INFO_PAGE;
    }
}
