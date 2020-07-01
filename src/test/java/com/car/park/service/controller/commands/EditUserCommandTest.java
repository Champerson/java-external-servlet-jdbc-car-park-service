package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.validation.UserValidationDto;
import com.car.park.service.controller.validation.UserValidationResultBuilder;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.User;
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
public class EditUserCommandTest {

    private static final String USER_OFFICE_PAGE = "WEB-INF/jsp/user-office-page.jsp";

    private static final long ID = 3;
    private static final String EMAIL = "test@gmail.com";
    private static final String PHONE = "0453623453";
    private static final String PHONE_INCORRECT = "23";
    private static final String NAME = "Name";
    private static final String AGE = "34";

    @InjectMocks
    EditUserCommand editUserCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getUserOfficeCommand;


    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    User user;

    @Test
    public void shouldExecuteGetUserOfficeCommandWhenValidationPassed() {
        when(session.getAttribute("userId")).thenReturn(ID);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("phone")).thenReturn(PHONE);
        when(request.getParameter("name")).thenReturn(NAME);
        when(request.getParameter("age")).thenReturn(AGE);
        when(request.getSession()).thenReturn(session);
        when(userDao.read(ID)).thenReturn(user);

        editUserCommand.execute(request, response);

        verify(getUserOfficeCommand).execute(request, response);
    }

    @Test
    public void shouldExecuteGetUserOfficeCommandWhenValidationFailed() {
        when(session.getAttribute("userId")).thenReturn(ID);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("phone")).thenReturn(PHONE_INCORRECT);
        when(request.getParameter("name")).thenReturn(NAME);
        when(request.getParameter("age")).thenReturn(AGE);
        when(request.getSession()).thenReturn(session);
        when(userDao.read(ID)).thenReturn(user);

        editUserCommand.execute(request, response);

        verify(getUserOfficeCommand).execute(request, response);
    }

    @Test
    public void shouldUpdateUserAndCommitTransactionWhenValidationPassed() {
        when(session.getAttribute("userId")).thenReturn(ID);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("phone")).thenReturn(PHONE);
        when(request.getParameter("name")).thenReturn(NAME);
        when(request.getParameter("age")).thenReturn(AGE);
        when(request.getSession()).thenReturn(session);
        when(userDao.read(ID)).thenReturn(user);

        editUserCommand.execute(request, response);

        verify(userDao).update(user);
        verify(transactionManager).commit();
    }

    @Test
    public void shouldSetUserValidationDtoWhenValidationNotPassed() {
        when(session.getAttribute("userId")).thenReturn(ID);
        when(userDao.read(ID)).thenReturn(user);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("phone")).thenReturn(PHONE_INCORRECT);
        when(request.getParameter("name")).thenReturn(NAME);
        when(request.getParameter("age")).thenReturn(AGE);
        when(request.getSession()).thenReturn(session);

        UserValidationDto userValidationDto = new UserValidationResultBuilder()
                .validateEmail(EMAIL)
                .validatePhone(PHONE_INCORRECT)
                .validateName(NAME)
                .validateAge(AGE)
                .errors();

        editUserCommand.execute(request, response);

        verify(session).setAttribute("validationResult", userValidationDto);
    }
}
