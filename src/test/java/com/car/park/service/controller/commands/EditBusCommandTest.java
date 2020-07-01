package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.BusValidationDto;
import com.car.park.service.controller.validation.BusValidationResultBuilder;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditBusCommandTest {

    private static final String ID = "2";
    private static final long ID_PARSED = 2;
    private static final String NUMBER = "AA2354AA";
    private static final String NUMBER_INCORRECT = "INCORRECT";
    private static final String NUMBER_FROM_DATABASE = "TE3333ST";
    private static final String MODEL = "bmw";
    private static final String MILEAGE = "10";
    private static final String PASSENGER_CAPACITY = "15";
    private static final String COLOUR_EN = "colour";
    private static final String COLOUR_UA = "колір";
    private static final String NOTES_EN = "notes";
    private static final String NOTES_UA = "нотатки";

    @InjectMocks
    EditBusCommand editBusCommand;
    @Mock
    private BusDao busDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getBusDetailsCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    Bus bus;
    @Mock
    BusValidationDto busValidationDto;

    @Test
    public void shouldExecuteGetBusDetailsCommand() {
        when(request.getParameter("busId")).thenReturn(ID);
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("model")).thenReturn(MODEL);
        when(request.getParameter("passengersCapacity")).thenReturn(PASSENGER_CAPACITY);
        when(request.getParameter("mileage")).thenReturn(MILEAGE);
        when(request.getParameter("colourEn")).thenReturn(COLOUR_EN);
        when(request.getParameter("colourUa")).thenReturn(COLOUR_UA);
        when(request.getParameter("notesEn")).thenReturn(NOTES_EN);
        when(request.getParameter("notesUa")).thenReturn(NOTES_UA);
        when(request.getSession()).thenReturn(session);
        when(bus.getNumber()).thenReturn(NUMBER_FROM_DATABASE);
        when(busValidationDto.getNumber()).thenReturn(NUMBER);
        when(busDao.read(ID_PARSED)).thenReturn(bus);

        editBusCommand.execute(request, response);

        verify(getBusDetailsCommand).execute(request, response);
    }

    @Test
    public void shouldUpdateBusAndCommitTransactionWhenValidationPassed() {
        when(request.getParameter("busId")).thenReturn(ID);
        when(request.getParameter("number")).thenReturn(NUMBER);
        when(request.getParameter("model")).thenReturn(MODEL);
        when(request.getParameter("passengersCapacity")).thenReturn(PASSENGER_CAPACITY);
        when(request.getParameter("mileage")).thenReturn(MILEAGE);
        when(request.getParameter("colourEn")).thenReturn(COLOUR_EN);
        when(request.getParameter("colourUa")).thenReturn(COLOUR_UA);
        when(request.getParameter("notesEn")).thenReturn(NOTES_EN);
        when(request.getParameter("notesUa")).thenReturn(NOTES_UA);
        when(request.getSession()).thenReturn(session);
        when(bus.getNumber()).thenReturn(NUMBER_FROM_DATABASE);
        when(busValidationDto.getNumber()).thenReturn(NUMBER);
        when(busDao.read(ID_PARSED)).thenReturn(bus);

        editBusCommand.execute(request, response);

        verify(busDao).update(bus);
        verify(transactionManager).commit();
    }

    @Test
    public void shouldSetValidationDtoToSessionWhenValidationNotPassed() {
        when(request.getParameter("busId")).thenReturn(ID);
        when(request.getParameter("number")).thenReturn(NUMBER_INCORRECT);
        when(request.getParameter("model")).thenReturn(MODEL);
        when(request.getParameter("passengersCapacity")).thenReturn(PASSENGER_CAPACITY);
        when(request.getParameter("mileage")).thenReturn(MILEAGE);
        when(request.getParameter("colourEn")).thenReturn(COLOUR_EN);
        when(request.getParameter("colourUa")).thenReturn(COLOUR_UA);
        when(request.getParameter("notesEn")).thenReturn(NOTES_EN);
        when(request.getParameter("notesUa")).thenReturn(NOTES_UA);
        when(request.getSession()).thenReturn(session);
        when(bus.getNumber()).thenReturn(NUMBER_FROM_DATABASE);
        when(busValidationDto.getNumber()).thenReturn(NUMBER_INCORRECT);
        when(busDao.read(ID_PARSED)).thenReturn(bus);

        BusValidationDto busValidationDto = new BusValidationResultBuilder()
                .validateNumber(NUMBER_INCORRECT)
                .validateModel(MODEL)
                .validatePassengersCapacity(PASSENGER_CAPACITY)
                .validateMileage(MILEAGE)
                .validateColourEn(COLOUR_EN)
                .validateColourUa(COLOUR_UA)
                .validateNotesEn(NOTES_EN)
                .validateNotesUa(NOTES_UA)
                .errors();

        editBusCommand.execute(request, response);

        verify(session).setAttribute("validationResult", busValidationDto);
    }
}
