package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.RouteValidationDto;
import com.car.park.service.controller.validation.RouteValidationResultBuilder;
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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditRouteCommandTest {

    private static final String ID = "2";
    private static final long ID_PARSED = 2;
    private static final String NUMBER = "177";
    private static final String NUMBER_INCORRECT = "INCORRECT";
    private static final String NUMBER_FROM_DATABASE = "445";
    private static final String LENGTH = "5234";
    private static final String DESCRIPTION_EN = "description";
    private static final String DESCRIPTION_UA = "опис";

    @InjectMocks
    EditRouteCommand editRouteCommand;
    @Mock
    private RouteDao routeDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getRouteDetails;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    Route route;
    @Mock
    RouteValidationDto routeValidationDto;

    @Test
    public void shouldExecuteGetRouteDetailsCommandWhenValidationPassed() {
        when(request.getParameter("routeId")).thenReturn(ID);
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("length")).thenReturn(LENGTH);
        when(request.getParameter("descriptionEn")).thenReturn(DESCRIPTION_EN);
        when(request.getParameter("descriptionUa")).thenReturn(DESCRIPTION_UA);
        when(request.getSession()).thenReturn(session);
        when(route.getNumber()).thenReturn(NUMBER_FROM_DATABASE);
        when(routeValidationDto.getNumber()).thenReturn(NUMBER);
        when(routeDao.read(ID_PARSED)).thenReturn(route);

        editRouteCommand.execute(request, response);

        verify(getRouteDetails).execute(request, response);
    }

    @Test
    public void shouldExecuteGetRouteDetailsCommandWhenValidationNotPassed() {
        when(request.getParameter("routeId")).thenReturn(ID);
        when(request.getParameter("number")).thenReturn(NUMBER_INCORRECT);
        when(request.getParameter("length")).thenReturn(LENGTH);
        when(request.getParameter("descriptionEn")).thenReturn(DESCRIPTION_EN);
        when(request.getParameter("descriptionUa")).thenReturn(DESCRIPTION_UA);
        when(request.getSession()).thenReturn(session);
        when(route.getNumber()).thenReturn(NUMBER_FROM_DATABASE);
        when(routeValidationDto.getNumber()).thenReturn(NUMBER_INCORRECT);
        when(routeDao.read(ID_PARSED)).thenReturn(route);

        editRouteCommand.execute(request, response);

        verify(getRouteDetails).execute(request, response);
    }

    @Test
    public void shouldUpdateRouteAndCommitTransactionWhenValidationPassed() {
        when(request.getParameter("routeId")).thenReturn(ID);
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("length")).thenReturn(LENGTH);
        when(request.getParameter("descriptionEn")).thenReturn(DESCRIPTION_EN);
        when(request.getParameter("descriptionUa")).thenReturn(DESCRIPTION_UA);
        when(request.getSession()).thenReturn(session);
        when(route.getNumber()).thenReturn(NUMBER_FROM_DATABASE);
        when(routeValidationDto.getNumber()).thenReturn(NUMBER);
        when(routeDao.read(ID_PARSED)).thenReturn(route);

        editRouteCommand.execute(request, response);

        verify(routeDao).update(route);
        verify(transactionManager).commit();
    }

    @Test
    public void shouldSetRouteValidationDtoToSessionWhenValidationNotPassed() {
        when(request.getParameter("routeId")).thenReturn(ID);
        when(request.getParameter("number")).thenReturn(NUMBER_INCORRECT);
        when(request.getParameter("length")).thenReturn(LENGTH);
        when(request.getParameter("descriptionEn")).thenReturn(DESCRIPTION_EN);
        when(request.getParameter("descriptionUa")).thenReturn(DESCRIPTION_UA);
        when(request.getSession()).thenReturn(session);
        when(route.getNumber()).thenReturn(NUMBER_FROM_DATABASE);
        when(routeValidationDto.getNumber()).thenReturn(NUMBER_INCORRECT);
        when(routeDao.read(ID_PARSED)).thenReturn(route);

        RouteValidationDto routeValidationDto = new RouteValidationResultBuilder()
                .validateNumber(NUMBER_INCORRECT)
                .validateLength(LENGTH)
                .validateDescriptionEn(DESCRIPTION_EN)
                .validateDescriptionUa(DESCRIPTION_UA)
                .errors();

        editRouteCommand.execute(request, response);

        verify(session).setAttribute("validationResult", routeValidationDto);
    }
}
