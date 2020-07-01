package com.car.park.service.controller.commands;

import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.BusDao;
import com.car.park.service.model.Assignment;
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

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllBusesCommandTest {

    private static final java.lang.String ALL_BUSES_PAGE = "WEB-INF/jsp/admin-bus-all-page.jsp";

    private static final long ID = 3;

    @InjectMocks
    GetAllBusesCommand getAllBusesCommand;
    @Mock
    private BusDao busDao;
    @Mock
    private AssignmentDao assignmentDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    Bus bus;
    @Mock
    Assignment assignment;

    @Test
    public void shouldReturnAllBusesPage() {
        when(bus.getId()).thenReturn(ID);
        when(busDao.readAll()).thenReturn(singletonList(bus));
        when(assignmentDao.readByBusId(ID)).thenReturn(assignment);

        String resultPage = getAllBusesCommand.execute(request, response);

        assertEquals(ALL_BUSES_PAGE, resultPage);
    }

    @Test
    public void shouldSetListOfAllBusesToRequest() {
        List<Bus> buses = new ArrayList<>();
        buses.add(bus);

        when(bus.getId()).thenReturn(ID);
        when(busDao.readAll()).thenReturn(buses);
        when(assignmentDao.readByBusId(ID)).thenReturn(assignment);

        getAllBusesCommand.execute(request, response);

        verify(request).setAttribute("buses", buses);
    }
}
