package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.RouteValidationDto;
import com.car.park.service.controller.validation.RouteValidationResultBuilder;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

/**
 * This command is responsible for Route update. It handles incoming parameters and validate them, so
 * in case of valid values updates Route, otherwise it sets RouteValidationDto to request and in any case
 * it returns to route details page
 */
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

        RouteValidationDto routeValidationDto = new RouteValidationResultBuilder()
                .validateNumber(number)
                .validateLength(length)
                .validateDescriptionEn(descriptionEn)
                .validateDescriptionUa(descriptionUa)
                .errors();
        checkRouteNewNumberExist(routeValidationDto, routeId);

        if (routeValidationDto.validationFailed()) {
            request.getSession().setAttribute("validationResult", routeValidationDto);
        } else {
            Route route = routeDao.read(routeId);
            route.setNumber(number);
            route.setLength(parseInt(length));
            route.getLocalizedDescription().put("en_EN", descriptionEn);
            route.getLocalizedDescription().put("uk_UA", descriptionUa);
            routeDao.update(route);
            transactionManager.commit();
            request.setAttribute("successMessage", "success.route.updated");
            request.getSession().removeAttribute("validationResult");
        }
        return getRouteDetails.execute(request, response);
    }

    private void checkRouteNewNumberExist(RouteValidationDto routeValidationDto, long routeId) {
        boolean busNumberModified = !routeDao.read(routeId).getNumber().equals(routeValidationDto.getNumber());
        if (routeValidationDto.getNumberError() == null && busNumberModified) {
            if (routeDao.read(routeValidationDto.getNumber()) != null) {
                routeValidationDto.setNumberError("validation.route.number.exist");
            }
        }
    }
}
