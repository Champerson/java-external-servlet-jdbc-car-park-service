package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.RouteValidationErrors;
import com.car.park.service.controller.validation.RouteValidationErrorsBuilder;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

public class CreateNewRouteCommand implements Command {

    private static final String NEW_ROUTE_PAGE = "WEB-INF/new-route.jsp";

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
        String number = request.getParameter("number");
        String length = request.getParameter("length");
        String descriptionEn = request.getParameter("descriptionEn");
        String descriptionUa = request.getParameter("descriptionUa");

        RouteValidationErrors routeValidationErrors = new RouteValidationErrorsBuilder()
                .validateNumber(number)
                .validateLength(length)
                .validateDescriptionEn(descriptionEn)
                .validateDescriptionUa(descriptionUa)
                .errors();

        if (routeValidationErrors.isPresent()) {
            request.setAttribute("validationErrors", routeValidationErrors);
            return NEW_ROUTE_PAGE;
        } else {
            Route route = new Route();
            route.setNumber(number);
            route.setLength(parseInt(length));
            route.getLocalizedDescription().put("en_EN", descriptionEn);
            route.getLocalizedDescription().put("uk_UA", descriptionUa);
            routeDao.create(route);
            transactionManager.commit();

            return getAllRoutesCommand.execute(request, response);
        }
    }
}
