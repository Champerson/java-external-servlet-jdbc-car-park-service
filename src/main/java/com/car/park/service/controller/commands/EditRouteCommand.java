package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.GET_ROUTE_DETAILS;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class EditRouteCommand implements Command {

    private final RouteDao routeDao;
    private final TransactionManager transactionManager;
    private final Command getRouteDetails;

    public EditRouteCommand(RouteDao routeDao, TransactionManager transactionManager, Command getRouteDetails) {
        this.routeDao = routeDao;
        this.transactionManager = transactionManager;
        this.getRouteDetails = getRouteDetails;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long routeId = parseLong(request.getParameter("routeId"));

        Route route = routeDao.read(routeId);
        route.setNumber(request.getParameter("number"));
        route.setLength(parseInt(request.getParameter("length")));
        route.getLocalizedDescription().put("en_EN", request.getParameter("descriptionEn"));
        route.getLocalizedDescription().put("uk_UA", request.getParameter("descriptionUa"));
        routeDao.update(route);
        transactionManager.commit();

        return getRouteDetails.execute(request, response);
    }
}
