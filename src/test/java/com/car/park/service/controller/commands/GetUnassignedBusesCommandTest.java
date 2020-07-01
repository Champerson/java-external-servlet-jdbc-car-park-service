package com.car.park.service.controller.commands;

import com.car.park.service.dao.BusDao;
import com.car.park.service.model.Bus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetUnassignedBusesCommandTest {

    private static final String UNASSIGNED_BUSES_PAGE = "WEB-INF/jsp/admin-route-assign-bus-page.jsp";

    private static final String ID = "ID";

    @InjectMocks
    GetUnassignedBusesCommand getUnassignedBusesCommand;
    @Mock
    private BusDao busDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    public void shouldReturnUnassignedBusesPage() {
        when(busDao.readAllWithoutAssignment()).thenReturn(emptyList());

        String resultPage = getUnassignedBusesCommand.execute(request, response);

        assertEquals(UNASSIGNED_BUSES_PAGE, resultPage);
    }

    @Test
    public void shouldSetRouteIdToRequest() {
        when(request.getParameter("routeId")).thenReturn(ID);

        getUnassignedBusesCommand.execute(request, response);

        verify(request).setAttribute("routeId", ID);
    }

    @Test
    public void shouldSetListOfAvailableBusesToRequest() {
        List<Bus> buses = new ArrayList<>();
        buses.add(new Bus());

        when(busDao.readAllWithoutAssignment()).thenReturn(buses);

        getUnassignedBusesCommand.execute(request, response);

        verify(request).setAttribute("buses", buses);
    }
}
