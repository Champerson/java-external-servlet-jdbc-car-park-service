package com.car.park.service.controller.commands;

import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.Bus;
import com.car.park.service.model.Route;
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
public class GetAllRoutesCommandTest {

    private static final String ALL_ROUTES_PAGE = "WEB-INF/jsp/admin-route-all-page.jsp";

    private static final long ID = 3;

    @InjectMocks
    GetAllRoutesCommand getAllRoutesCommand;
    @Mock
    private RouteDao routeDao;
    @Mock
    private AssignmentDao assignmentDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    Route route;
    @Mock
    Assignment assignment;

    @Test
    public void shouldReturnAllRoutesPage() {
        when(routeDao.readAll()).thenReturn(singletonList(route));
        when(route.getId()).thenReturn(ID);
        when(assignmentDao.readByRouteId(ID)).thenReturn(singletonList(assignment));

        String resultPage = getAllRoutesCommand.execute(request, response);

        assertEquals(ALL_ROUTES_PAGE, resultPage);
    }

    @Test
    public void shouldSetListOfAllRoutesToRequest() {
        List<Route> routes = new ArrayList<>();
        routes.add(route);

        when(routeDao.readAll()).thenReturn(singletonList(route));
        when(route.getId()).thenReturn(ID);
        when(assignmentDao.readByRouteId(ID)).thenReturn(singletonList(assignment));

        getAllRoutesCommand.execute(request, response);

        verify(request).setAttribute("routes", routes);
    }
}
