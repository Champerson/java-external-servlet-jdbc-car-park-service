package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

public class CreateNewBusCommand implements Command {

    private final BusDao busDao;
    private final TransactionManager transactionManager;
    private final Command getAllBusesCommand;

    public CreateNewBusCommand(BusDao busDao, TransactionManager transactionManager, Command getAllBusesCommand) {
        this.busDao = busDao;
        this.transactionManager = transactionManager;
        this.getAllBusesCommand = getAllBusesCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Bus bus = new Bus();
        bus.setNumber(request.getParameter("number"));
        bus.setModel(request.getParameter("model"));
        bus.setPassengersCapacity(parseInt(request.getParameter("passengersCapacity")));
        bus.setMileage(parseInt(request.getParameter("mileage")));
        bus.getLocalizedColour().put("en_EN", request.getParameter("colourEn"));
        bus.getLocalizedColour().put("uk_UA", request.getParameter("colourUa"));
        bus.getLocalizedNotes().put("en_EN", request.getParameter("notesEn"));
        bus.getLocalizedNotes().put("uk_UA", request.getParameter("notesUa"));
        busDao.create(bus);
        transactionManager.commit();

        return getAllBusesCommand.execute(request, response);
    }
}
