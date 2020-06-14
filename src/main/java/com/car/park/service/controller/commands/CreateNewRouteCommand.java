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

public class CreateNewRouteCommand implements Command {

    private static final String NEW_ROUTE_PAGE = "WEB-INF/jsp/admin-route-create-page.jsp";

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

        RouteValidationDto routeValidationDto = new RouteValidationResultBuilder()
                .validateNumber(number)
                .validateLength(length)
                .validateDescriptionEn(descriptionEn)
                .validateDescriptionUa(descriptionUa)
                .errors();
        checkRouteNumberExist(routeValidationDto);

        if (routeValidationDto.validationFailed()) {
            request.getSession().setAttribute("validationResult", routeValidationDto);
            return NEW_ROUTE_PAGE;
        } else {
            Route route = new Route();
            route.setNumber(number);
            route.setLength(parseInt(length));
            route.getLocalizedDescription().put("en_EN", descriptionEn);
            route.getLocalizedDescription().put("uk_UA", descriptionUa);
            routeDao.create(route);
            transactionManager.commit();
            request.setAttribute("successMessage", "success.route.created");
            request.getSession().removeAttribute("validationResult");
            return getAllRoutesCommand.execute(request, response);
        }
    }

    private void checkRouteNumberExist(RouteValidationDto routeValidationDto) {
        if (routeValidationDto.getNumberError() == null) {
            if (routeDao.read(routeValidationDto.getNumber()) != null) {
                routeValidationDto.setNumberError("validation.route.number.exist");
            }
        }
    }
}
