package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.BusValidationErrors;
import com.car.park.service.controller.validation.BusValidationErrorsBuilder;
import com.car.park.service.controller.validation.UserValidationErrors;
import com.car.park.service.controller.validation.UserValidationErrorsBuilder;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class EditBusCommand implements Command {

    private final BusDao busDao;
    private final TransactionManager transactionManager;
    private final Command getAllBusesCommand;

    public EditBusCommand(BusDao busDao, TransactionManager transactionManager, Command getAllBusesCommand) {
        this.busDao = busDao;
        this.transactionManager = transactionManager;
        this.getAllBusesCommand = getAllBusesCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long busId = parseLong(request.getParameter("busId"));

        String number = request.getParameter("number");
        String model = request.getParameter("model");
        String passengersCapacity = request.getParameter("passengersCapacity");
        String mileage = request.getParameter("mileage");
        String colourEn = request.getParameter("colourEn");
        String colourUa = request.getParameter("colourUa");
        String notesEn = request.getParameter("notesEn");
        String notesUa = request.getParameter("notesUa");

        BusValidationErrors busValidationErrors = new BusValidationErrorsBuilder()
                .validateNumber(number)
                .validateModel(model)
                .validatePassengersCapacity(passengersCapacity)
                .validateMileage(mileage)
                .validateColourEn(colourEn)
                .validateColourUa(colourUa)
                .validateNotesEn(notesEn)
                .validateNotesUa(notesUa)
                .errors();

        if (busValidationErrors.isPresent()) {
            request.setAttribute("validationErrors", busValidationErrors);
        } else {
            Bus bus = busDao.read(busId);
            bus.setNumber(number);
            bus.setModel(model);
            bus.setPassengersCapacity(parseInt(passengersCapacity));
            bus.setMileage(parseInt(mileage));
            bus.getLocalizedColour().put("en_EN", colourEn);
            bus.getLocalizedColour().put("uk_UA", colourUa);
            bus.getLocalizedNotes().put("en_EN", notesEn);
            bus.getLocalizedNotes().put("uk_UA", notesUa);
            busDao.update(bus);
            transactionManager.commit();
        }
        return getAllBusesCommand.execute(request, response);
    }
}
