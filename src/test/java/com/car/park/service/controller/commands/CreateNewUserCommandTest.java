package com.car.park.service.controller.commands;

import com.car.park.service.controller.support.PasswordEncoder;
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

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateNewUserCommandTest {

    private static final String INDEX_PAGE = "index.jsp";
    private static final String REGISTRATION_PAGE = "WEB-INF/jsp/registration-page.jsp";

    private static final String LOGIN_INCORRECT = "LOGIN_LOGIN";
    private static final String LOGIN = "login123";
    private static final String PASSWORD = "password123";
    private static final String EMAIL = "test@gmail.com";
    private static final String PHONE = "0453623453";
    private static final String NAME = "Name";
    private static final String AGE = "34";

    @InjectMocks
    CreateNewUserCommand createNewUserCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @Test
    public void shouldReturnIndexPage() {
        when(request.getParameter("login")).thenReturn(LOGIN);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("phone")).thenReturn(PHONE);
        when(request.getParameter("name")).thenReturn(NAME);
        when(request.getParameter("age")).thenReturn(AGE);
        when(request.getSession()).thenReturn(session);

        String resultPage = createNewUserCommand.execute(request, response);

        assertEquals(INDEX_PAGE, resultPage);
    }

    @Test
    public void shouldReturnRegistrationPage() {
        when(request.getParameter("login")).thenReturn(LOGIN_INCORRECT);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("phone")).thenReturn(PHONE);
        when(request.getParameter("name")).thenReturn(NAME);
        when(request.getParameter("age")).thenReturn(AGE);
        when(request.getSession()).thenReturn(session);

        String resultPage = createNewUserCommand.execute(request, response);

        assertEquals(REGISTRATION_PAGE, resultPage);
    }

    @Test
    public void shouldCreateNewUserAndCommitTransaction() {
        when(request.getParameter("login")).thenReturn(LOGIN);
        when(request.getParameter("password")).thenReturn(PASSWORD);
        when(request.getParameter("email")).thenReturn(EMAIL);
        when(request.getParameter("phone")).thenReturn(PHONE);
        when(request.getParameter("name")).thenReturn(NAME);
        when(request.getParameter("age")).thenReturn(AGE);
        when(request.getSession()).thenReturn(session);

        createNewUserCommand.execute(request, response);

        verify(userDao).create(any(User.class));
        verify(transactionManager).commit();
    }
}
