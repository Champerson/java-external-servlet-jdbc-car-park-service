package com.car.park.service.controller.commands;

import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.Route;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetRouteDetailsCommandTest {

    private static final String ROUTE_INFO_PAGE = "WEB-INF/jsp/admin-route-details-page.jsp";

    private static final String ID = "3";
    private static final long ID_PARSED = 3;
    private static final String COMMAND = "get_route_details";

    @InjectMocks
    GetRouteDetailsCommand getRouteDetailsCommand;
    @Mock
    private RouteDao routeDao;
    @Mock
    private AssignmentDao assignmentDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    Route route;
    @Mock
    Assignment assignment;

    @Test
    public void shouldReturnRouteInfoPage() {
        when(assignmentDao.readByRouteId(ID_PARSED)).thenReturn(singletonList(assignment));
        when(request.getParameter("command")).thenReturn(COMMAND);
        when(request.getParameter("local")).thenReturn(null);
        when(request.getParameter("routeId")).thenReturn(ID);
        when(routeDao.read(ID_PARSED)).thenReturn(route);
        when(request.getSession()).thenReturn(session);

        String resultPage = getRouteDetailsCommand.execute(request, response);

        assertEquals(ROUTE_INFO_PAGE, resultPage);
    }

    @Test
    public void shouldSetRouteToRequest() {
        when(assignmentDao.readByRouteId(ID_PARSED)).thenReturn(singletonList(assignment));
        when(request.getParameter("command")).thenReturn(COMMAND);
        when(request.getParameter("local")).thenReturn(null);
        when(request.getParameter("routeId")).thenReturn(ID);
        when(routeDao.read(ID_PARSED)).thenReturn(route);
        when(request.getSession()).thenReturn(session);

        getRouteDetailsCommand.execute(request, response);

        verify(request).setAttribute("route", route);
    }
}
