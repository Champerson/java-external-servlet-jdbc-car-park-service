package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

public class DeleteBusCommand implements Command {

    private final BusDao busDao;
    private final TransactionManager transactionManager;
    private final Command getAllBusesCommand;

    public DeleteBusCommand(BusDao busDao, TransactionManager transactionManager, Command getAllBusesCommand) {
        this.busDao = busDao;
        this.transactionManager = transactionManager;
        this.getAllBusesCommand = getAllBusesCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long busId = parseLong(request.getParameter("busId"));
        busDao.delete(busId);
        transactionManager.commit();
        return getAllBusesCommand.execute(request, response);
    }
}
