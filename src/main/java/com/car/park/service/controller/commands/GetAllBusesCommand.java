package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.BusDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllBusesCommand implements Command {

    private static final String ALL_BUSES_PAGE = "WEB-INF/all-buses.jsp";

    private final BusDao busDao;

    public GetAllBusesCommand(BusDao busDao) {
        this.busDao = busDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("buses", busDao.readAll());
        return ALL_BUSES_PAGE;
    }
}
