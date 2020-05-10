package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.DaoFactory;
import com.car.park.service.dao.RouteDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllRoutesCommand implements Command {

    private static final String ALL_ROUTES_PAGE = "WEB-INF/all-routes.jsp";

    private final RouteDao routeDao;

    public GetAllRoutesCommand(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("routes", routeDao.readAll());
        return ALL_ROUTES_PAGE;
    }
}
