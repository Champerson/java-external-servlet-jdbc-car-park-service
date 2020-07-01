package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteBusCommandTest {

    private static final String ID = "3";
    private static final long ID_PARSED = 3;

    @InjectMocks
    DeleteBusCommand deleteBusCommand;
    @Mock
    private BusDao busDao;
    @Mock
    private AssignmentDao assignmentDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getAllBusesCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    Assignment assignment;

    @Test
    public void shouldExecuteGetAllBusesCommand() {
        when(request.getParameter("busId")).thenReturn(ID);
        when(assignmentDao.readByBusId(ID_PARSED)).thenReturn(assignment);

        deleteBusCommand.execute(request, response);

        verify(getAllBusesCommand).execute(request, response);
    }

    @Test
    public void shouldDeleteBusWithAssignment() {
        when(assignment.getId()).thenReturn(ID_PARSED);
        when(request.getParameter("busId")).thenReturn(ID);
        when(assignmentDao.readByBusId(ID_PARSED)).thenReturn(assignment);

        deleteBusCommand.execute(request, response);

        verify(busDao).delete(ID_PARSED);
        verify(assignmentDao).delete(ID_PARSED);
    }

    @Test
    public void shouldDeleteBusWithoutAssignment() {
        when(request.getParameter("busId")).thenReturn(ID);
        when(assignmentDao.readByBusId(ID_PARSED)).thenReturn(null);

        deleteBusCommand.execute(request, response);

        verify(busDao).delete(ID_PARSED);
    }

    @Test
    public void shouldCommitTransaction() {
        when(request.getParameter("busId")).thenReturn(ID);
        when(assignmentDao.readByBusId(ID_PARSED)).thenReturn(any());

        deleteBusCommand.execute(request, response);

        verify(transactionManager).commit();
    }
}
