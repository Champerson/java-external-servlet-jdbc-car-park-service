package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.model.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command shows all Routes from database on ALL_ROUTES_PAGE
 */
public class GetAllRoutesCommand implements Command {

    private static final String ALL_ROUTES_PAGE = "WEB-INF/jsp/admin-route-all-page.jsp";

    private final RouteDao routeDao;
    private final AssignmentDao assignmentDao;

    public GetAllRoutesCommand(RouteDao routeDao, AssignmentDao assignmentDao) {
        this.routeDao = routeDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Route> routes = routeDao.readAll();
        for (Route route : routes) {
            route.setAssignments(assignmentDao.readByRouteId(route.getId()));
        }
        request.setAttribute("routes", routes);
        return ALL_ROUTES_PAGE;
    }
}
