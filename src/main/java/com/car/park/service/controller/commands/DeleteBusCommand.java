package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.lang.Long.parseLong;

/**
 * This command deletes Bus and it's Assignment if exist
 */
public class DeleteBusCommand implements Command {

    private final BusDao busDao;
    private final AssignmentDao assignmentDao;
    private final TransactionManager transactionManager;
    private final Command getAllBusesCommand;

    public DeleteBusCommand(
            BusDao busDao, AssignmentDao assignmentDao,
            TransactionManager transactionManager, Command getAllBusesCommand
    ) {
        this.busDao = busDao;
        this.assignmentDao = assignmentDao;
        this.transactionManager = transactionManager;
        this.getAllBusesCommand = getAllBusesCommand;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long busId = parseLong(request.getParameter("busId"));
        Assignment busAssignment = assignmentDao.readByBusId(busId);
        if (busAssignment != null) {
            assignmentDao.delete(busAssignment.getId());
        }
        busDao.delete(busId);
        transactionManager.commit();
        request.setAttribute("successMessage", "success.bus.deleted");
        return getAllBusesCommand.execute(request, response);
    }
}
