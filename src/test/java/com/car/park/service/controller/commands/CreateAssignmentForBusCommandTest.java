package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.BusDao;
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

import static java.lang.String.valueOf;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateAssignmentForBusCommandTest {

    private static final long BUS_ID = 1;
    private static final long ROUTE_ID = 2;

    @InjectMocks
    private CreateAssignmentForBusCommand createAssignmentForBusCommand;
    @Mock
    private RouteDao routeDao;
    @Mock
    private BusDao busDao;
    @Mock
    private AssignmentDao assignmentDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getRouteDetailsCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    public void shouldExecuteGetRouteDetailsCommand() {
        when(request.getParameter("busId")).thenReturn(valueOf(BUS_ID));
        when(request.getParameter("routeId")).thenReturn(valueOf(ROUTE_ID));

        createAssignmentForBusCommand.execute(request, response);

        verify(getRouteDetailsCommand).execute(request, response);
    }

    @Test
    public void shouldCreateAssignment() {
        when(request.getParameter("busId")).thenReturn(valueOf(BUS_ID));
        when(request.getParameter("routeId")).thenReturn(valueOf(ROUTE_ID));

        createAssignmentForBusCommand.execute(request, response);

        verify(busDao).read(BUS_ID);
        verify(routeDao).read(ROUTE_ID);
        verify(assignmentDao).create(any(Assignment.class));
    }

    @Test
    public void shouldCommitTransaction() {
        when(request.getParameter("busId")).thenReturn(valueOf(BUS_ID));
        when(request.getParameter("routeId")).thenReturn(valueOf(ROUTE_ID));

        createAssignmentForBusCommand.execute(request, response);

        verify(transactionManager).commit();
    }
}
