package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.BusDao;
import com.car.park.service.model.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * This command shows all Buses from database on ALL_BUSES_PAGE
 */
public class GetAllBusesCommand implements Command {

    private static final String ALL_BUSES_PAGE = "WEB-INF/jsp/admin-bus-all-page.jsp";

    private final BusDao busDao;
    private final AssignmentDao assignmentDao;

    public GetAllBusesCommand(BusDao busDao, AssignmentDao assignmentDao) {
        this.busDao = busDao;
        this.assignmentDao = assignmentDao;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Bus> buses = busDao.readAll();
        for (Bus bus : buses) {
            bus.setAssignment(assignmentDao.readByBusId(bus.getId()));
        }
        request.setAttribute("buses", buses);
        return ALL_BUSES_PAGE;
    }
}
