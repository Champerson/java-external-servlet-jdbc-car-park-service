package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.BusDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUnassignedBusesCommand implements Command {

    private static final String UNASSIGNED_BUSES_PAGE = "WEB-INF/unassigned-buses.jsp";

    private final BusDao busDao;

    public GetUnassignedBusesCommand(BusDao busDao) {
        this.busDao = busDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("routeId", request.getParameter("routeId"));
        request.setAttribute("buses", busDao.readAllWithoutAssignment());
        return UNASSIGNED_BUSES_PAGE;
    }
}
