package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.BusDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.controller.CommandMapping.Commands.EDIT_BUS;
import static java.lang.Long.parseLong;

public class GetBusDetailsCommand implements Command {

    private static final String BUS_INFO_PAGE = "WEB-INF/jsp/admin-bus-details-page.jsp";

    private final BusDao busDao;

    public GetBusDetailsCommand(BusDao busDao) {
        this.busDao = busDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        cleanUpValidationResult(request, EDIT_BUS);
        long busId = parseLong(request.getParameter("busId"));
        request.setAttribute("bus", busDao.read(busId));
        return BUS_INFO_PAGE;
    }
}
