package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.BusDao;
import com.car.park.service.dao.support.TransactionManager;
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
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewBusCommandTest {

    private static final String NEW_BUS_PAGE = "WEB-INF/jsp/admin-bus-create-page.jsp";

    private static final String NUMBER = "AA2354AA";
    private static final String NUMBER_INCORRECT = "num";
    private static final String MODEL = "bmw";
    private static final String MILEAGE = "10";
    private static final String PASSENGER_CAPACITY = "15";
    private static final String COLOUR_EN = "colour";
    private static final String COLOUR_UA = "колір";
    private static final String NOTES_EN = "notes";
    private static final String NOTES_UA = "нотатки";

    @InjectMocks
    CreateNewBusCommand createNewBusCommand;
    @Mock
    private BusDao busDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getAllBusesCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Test
    public void shouldExecuteGetAllBusesCommand() {
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("model")).thenReturn(MODEL);
        when(request.getParameter("passengersCapacity")).thenReturn(PASSENGER_CAPACITY);
        when(request.getParameter("mileage")).thenReturn(MILEAGE);
        when(request.getParameter("colourEn")).thenReturn(COLOUR_EN);
        when(request.getParameter("colourUa")).thenReturn(COLOUR_UA);
        when(request.getParameter("notesEn")).thenReturn(NOTES_EN);
        when(request.getParameter("notesUa")).thenReturn(NOTES_UA);
        when(request.getSession()).thenReturn(session);

        createNewBusCommand.execute(request, response);

        verify(getAllBusesCommand).execute(request, response);
    }

    @Test
    public void shouldReturnNewBusPage() {
        when(request.getParameter("number")).thenReturn(NUMBER_INCORRECT);
        when(request.getParameter("model")).thenReturn(MODEL);
        when(request.getParameter("passengersCapacity")).thenReturn(PASSENGER_CAPACITY);
        when(request.getParameter("mileage")).thenReturn(MILEAGE);
        when(request.getParameter("colourEn")).thenReturn(COLOUR_EN);
        when(request.getParameter("colourUa")).thenReturn(COLOUR_UA);
        when(request.getParameter("notesEn")).thenReturn(NOTES_EN);
        when(request.getParameter("notesUa")).thenReturn(NOTES_UA);
        when(request.getSession()).thenReturn(session);

        String resultPage = createNewBusCommand.execute(request, response);

        assertEquals(NEW_BUS_PAGE, resultPage);
    }

    @Test
    public void shouldCreateNewBus() {
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("model")).thenReturn(MODEL);
        when(request.getParameter("passengersCapacity")).thenReturn(PASSENGER_CAPACITY);
        when(request.getParameter("mileage")).thenReturn(MILEAGE);
        when(request.getParameter("colourEn")).thenReturn(COLOUR_EN);
        when(request.getParameter("colourUa")).thenReturn(COLOUR_UA);
        when(request.getParameter("notesEn")).thenReturn(NOTES_EN);
        when(request.getParameter("notesUa")).thenReturn(NOTES_UA);
        when(request.getSession()).thenReturn(session);

        createNewBusCommand.execute(request, response);

        verify(busDao).create(any(Bus.class));
        verify(transactionManager).commit();
    }
}
