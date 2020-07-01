package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DeleteRouteCommandTest {

    private static final String ID = "2";
    private static final long ID_PARSED = 2;
    private static final long ID_ASSIGNMENT = 4;

    @InjectMocks
    DeleteRouteCommand deleteRouteCommand;
    @Mock
    private RouteDao routeDao;
    @Mock
    private AssignmentDao assignmentDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getAllRoutesCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    public void shouldExecuteGetAllRoutesCommand() {
        List<Assignment> assignments = new ArrayList<>();
        Assignment assignment = new Assignment();
        assignments.add(assignment);

        when(request.getParameter("routeId")).thenReturn(ID);
        when(assignmentDao.readByRouteId(ID_PARSED)).thenReturn(assignments);

        deleteRouteCommand.execute(request, response);

        verify(getAllRoutesCommand).execute(request, response);
    }

    @Test
    public void ShouldDeleteRouteWithAssignments() {
        List<Assignment> assignments = new ArrayList<>();
        Assignment assignment = new Assignment();
        assignment.setId(ID_ASSIGNMENT);
        assignments.add(assignment);

        when(request.getParameter("routeId")).thenReturn(ID);
        when(assignmentDao.readByRouteId(ID_PARSED)).thenReturn(assignments);

        deleteRouteCommand.execute(request, response);

        verify(assignmentDao).delete(ID_ASSIGNMENT);
        verify(routeDao).delete(ID_PARSED);
    }

    @Test
    public void shouldCommitTransaction() {
        List<Assignment> assignments = new ArrayList<>();
        Assignment assignment = new Assignment();
        assignments.add(assignment);

        when(request.getParameter("routeId")).thenReturn(ID);
        when(assignmentDao.readByRouteId(ID_PARSED)).thenReturn(assignments);

        deleteRouteCommand.execute(request, response);

        verify(transactionManager).commit();
    }
}
