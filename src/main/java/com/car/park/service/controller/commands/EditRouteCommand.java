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
        } else {
            Route route = routeDao.read(routeId);
            route.setNumber(number);
            route.setLength(parseInt(length));
            route.getLocalizedDescription().put("en_EN", descriptionEn);
            route.getLocalizedDescription().put("uk_UA", descriptionUa);
            routeDao.update(route);
            transactionManager.commit();

        }
        return getRouteDetails.execute(request, response);
    }
}
