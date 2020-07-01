package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.RouteDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Route;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewRouteCommandTest {

    private static final String NEW_ROUTE_PAGE = "WEB-INF/jsp/admin-route-create-page.jsp";

    private static final String NUMBER = "177";
    private static final String NUMBER_INCORRECT = "num";
    private static final String LENGTH = "5234";
    private static final String DESCRIPTION_EN = "description";
    private static final String DESCRIPTION_UA = "опис";

    @InjectMocks
    CreateNewRouteCommand createNewRouteCommand;
    @Mock
    private RouteDao routeDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getAllRoutesCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Test
    public void shouldReturnNewRoutePage() {
        when(request.getParameter("number")).thenReturn(NUMBER_INCORRECT);
        when(request.getParameter("length")).thenReturn(LENGTH);
        when(request.getParameter("descriptionEn")).thenReturn(DESCRIPTION_EN);
        when(request.getParameter("descriptionUa")).thenReturn(DESCRIPTION_UA);
        when(request.getSession()).thenReturn(session);

        String resultPage = createNewRouteCommand.execute(request, response);

        assertEquals(NEW_ROUTE_PAGE, resultPage);
    }

    @Test
    public void shouldExecuteGetAllRoutesCommand() {
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("length")).thenReturn(LENGTH);
        when(request.getParameter("descriptionEn")).thenReturn(DESCRIPTION_EN);
        when(request.getParameter("descriptionUa")).thenReturn(DESCRIPTION_UA);
        when(request.getSession()).thenReturn(session);

        createNewRouteCommand.execute(request, response);

        verify(getAllRoutesCommand).execute(request, response);
    }

    @Test
    public void shouldCreateNewRouteAndCommitTransaction() {
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("length")).thenReturn(LENGTH);
        when(request.getParameter("descriptionEn")).thenReturn(DESCRIPTION_EN);
        when(request.getParameter("descriptionUa")).thenReturn(DESCRIPTION_UA);
        when(request.getSession()).thenReturn(session);

        createNewRouteCommand.execute(request, response);

        verify(routeDao).create(any(Route.class));
        verify(transactionManager).commit();
    }
}
