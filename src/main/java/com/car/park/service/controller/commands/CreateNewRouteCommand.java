package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

public class CreateNewRouteCommand implements Command {

    private final RouteDao routeDao;
    private final TransactionManager transactionManager;
    private final Command getAllRoutesCommand;

    public CreateNewRouteCommand(RouteDao routeDao, TransactionManager transactionManager, Command getAllRoutesCommand) {
        this.routeDao = routeDao;
        this.transactionManager = transactionManager;
        this.getAllRoutesCommand = getAllRoutesCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Route route = new Route();
        route.setNumber(request.getParameter("number"));
        route.setLength(parseInt(request.getParameter("length")));
        route.getLocalizedDescription().put("en_EN", request.getParameter("descriptionEn"));
        route.getLocalizedDescription().put("uk_UA", request.getParameter("descriptionUa"));
        routeDao.create(route);
        transactionManager.commit();

        return getAllRoutesCommand.execute(request, response);
    }
}
