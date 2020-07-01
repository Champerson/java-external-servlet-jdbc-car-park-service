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
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetBusDetailsCommandTest {

    private static final String BUS_INFO_PAGE = "WEB-INF/jsp/admin-bus-details-page.jsp";

    private static final String ID = "3";
    private static final long ID_PARSED = 3;
    private static final String COMMAND = "get_bus_details";

    @InjectMocks
    GetBusDetailsCommand getBusDetailsCommand;
    @Mock
    private BusDao busDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    Bus bus;

    @Test
    public void shouldReturnBusInfoPage() {
        when(request.getParameter("command")).thenReturn(COMMAND);
        when(request.getParameter("local")).thenReturn(null);
        when(request.getParameter("busId")).thenReturn(ID);
        when(request.getSession()).thenReturn(session);
        when(busDao.read(ID_PARSED)).thenReturn(bus);

        String resultPage = getBusDetailsCommand.execute(request, response);

        assertEquals(BUS_INFO_PAGE, resultPage);
    }

    @Test
    public void shouldSetBusToRequest() {
        when(request.getParameter("command")).thenReturn(COMMAND);
        when(request.getParameter("local")).thenReturn(null);
        when(request.getParameter("busId")).thenReturn(ID);
        when(request.getSession()).thenReturn(session);
        when(busDao.read(ID_PARSED)).thenReturn(bus);

        getBusDetailsCommand.execute(request, response);

        verify(request).setAttribute("bus", bus);
    }
}
