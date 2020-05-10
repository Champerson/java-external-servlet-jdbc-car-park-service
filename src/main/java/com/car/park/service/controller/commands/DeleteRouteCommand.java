package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

public class DeleteRouteCommand implements Command {

    private final RouteDao routeDao;
    private final TransactionManager transactionManager;
    private final Command getAllRoutesCommand;

    public DeleteRouteCommand(RouteDao routeDao, TransactionManager transactionManager, Command getAllRoutesCommand) {
        this.routeDao = routeDao;
        this.transactionManager = transactionManager;
        this.getAllRoutesCommand = getAllRoutesCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long routeId = parseLong(request.getParameter("routeId"));
        routeDao.delete(routeId);
        transactionManager.commit();
        return getAllRoutesCommand.execute(request, response);
    }
}
