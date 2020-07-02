package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.BusValidationDto;
import com.car.park.service.controller.validation.BusValidationResultBuilder;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Integer.parseInt;

/**
 * This command is responsible for Bus creation. It handles incoming parameters and validate them, so
 * in case of valid values creates new Bus, otherwise it sets BusValidationDto to request and returns to NEW_BUS_PAGE
 */
public class CreateNewBusCommand implements Command {

    private static final String NEW_BUS_PAGE = "WEB-INF/jsp/admin-bus-create-page.jsp";

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
        String number = request.getParameter("number");
        String model = request.getParameter("model");
        String passengersCapacity = request.getParameter("passengersCapacity");
        String mileage = request.getParameter("mileage");
        String colourEn = request.getParameter("colourEn");
        String colourUa = request.getParameter("colourUa");
        String notesEn = request.getParameter("notesEn");
        String notesUa = request.getParameter("notesUa");

        BusValidationDto busValidationDto = new BusValidationResultBuilder()
                .validateNumber(number)
                .validateModel(model)
                .validatePassengersCapacity(passengersCapacity)
                .validateMileage(mileage)
                .validateColourEn(colourEn)
                .validateColourUa(colourUa)
                .validateNotesEn(notesEn)
                .validateNotesUa(notesUa)
                .errors();
        checkBusNumberExist(busValidationDto);

        if (busValidationDto.validationFailed()) {
            request.getSession().setAttribute("validationResult", busValidationDto);
            return NEW_BUS_PAGE;
        } else {
            Bus bus = new Bus();
            bus.setNumber(number);
            bus.setModel(model);
            bus.setPassengersCapacity(parseInt(passengersCapacity));
            bus.setMileage(parseInt(mileage));
            bus.getLocalizedColour().put("en_EN", colourEn);
            bus.getLocalizedColour().put("uk_UA", colourUa);
            bus.getLocalizedNotes().put("en_EN", notesEn);
            bus.getLocalizedNotes().put("uk_UA", notesUa);
            busDao.create(bus);
            transactionManager.commit();
            request.setAttribute("successMessage", "success.bus.created");
            request.getSession().removeAttribute("validationResult");
            return getAllBusesCommand.execute(request, response);
        }
    }

    private void checkBusNumberExist(BusValidationDto busValidationDto) {
        if (busValidationDto.getNumberError() == null) {
            if (busDao.read(busValidationDto.getNumber()) != null) {
                busValidationDto.setNumberError("validation.bus.number.exist");
            }
        }
    }
}
