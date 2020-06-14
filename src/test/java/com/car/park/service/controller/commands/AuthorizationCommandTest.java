package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.controller.support.PasswordEncoder;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.car.park.service.model.UserRole.ROLE_ADMIN;
import static com.car.park.service.model.UserRole.ROLE_DRIVER;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthorizationCommandTest {

    private static final String INDEX_PAGE = "index.jsp";
    private static final String ADMIN_MENU_PAGE = "WEB-INF/jsp/admin-menu-page.jsp";
    private static final String USER_PASSWORD = "password";
    private static final String USER_LOGIN = "login";
    private static final long USER_ID = 1;

    @InjectMocks
    private AuthorizationCommand authorizationCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private Command getUserOfficeCommand;

    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private HttpSession httpSession;
    @Mock
    private User user;

    @Test
    public void shouldReturnIndexPageWhenNoUserFound() {
        String resultPage = authorizationCommand.execute(httpServletRequest, httpServletResponse);

        assertEquals(INDEX_PAGE, resultPage);
    }

    @Test
    public void shouldReturnIndexPageWhenPasswordNotMatch() {
        when(userDao.read(any())).thenReturn(user);
        when(user.getPassword()).thenReturn(USER_PASSWORD);

        String resultPage = authorizationCommand.execute(httpServletRequest, httpServletResponse);

        assertEquals(INDEX_PAGE, resultPage);
    }

    @Test
    public void shouldSetUserIdToSessionWhenCredentialsMatch() {
        when(passwordEncoder.encode(any())).thenReturn(USER_PASSWORD);
        when(userDao.read(any())).thenReturn(user);
        when(user.getPassword()).thenReturn(USER_PASSWORD);
        when(user.getId()).thenReturn(USER_ID);
        when(httpServletRequest.getSession(true)).thenReturn(httpSession);
        when(httpServletRequest.getParameter("login")).thenReturn(USER_LOGIN);
        when(httpServletRequest.getParameter("password")).thenReturn(USER_PASSWORD);

        authorizationCommand.execute(httpServletRequest, httpServletResponse);

        verify(httpSession).setAttribute("userId", USER_ID);
    }

    @Test
    public void shouldReturnIndexPageWhenRoleNotAdminOrUser() {
        when(passwordEncoder.encode(any())).thenReturn(USER_PASSWORD);
        when(userDao.read(any())).thenReturn(user);
        when(user.getPassword()).thenReturn(USER_PASSWORD);
        when(user.getId()).thenReturn(USER_ID);
        when(httpServletRequest.getSession(true)).thenReturn(httpSession);
        when(httpServletRequest.getParameter("login")).thenReturn(USER_LOGIN);
        when(httpServletRequest.getParameter("password")).thenReturn(USER_PASSWORD);

        String resultPage = authorizationCommand.execute(httpServletRequest, httpServletResponse);

        assertEquals(INDEX_PAGE, resultPage);
    }

    @Test
    public void shouldReturnIndexPageWhenCredentialsInvalid() {
        when(passwordEncoder.encode(any())).thenReturn(USER_PASSWORD);
        when(userDao.read(any())).thenReturn(user);
        when(user.getPassword()).thenReturn(USER_PASSWORD);
        when(user.getId()).thenReturn(USER_ID);
        when(httpServletRequest.getSession(true)).thenReturn(httpSession);

        String resultPage = authorizationCommand.execute(httpServletRequest, httpServletResponse);

        assertEquals(INDEX_PAGE, resultPage);
    }

    @Test
    public void shouldReturnAdminPageWhenRoleAdmin() {
        when(passwordEncoder.encode(any())).thenReturn(USER_PASSWORD);
        when(userDao.read(any())).thenReturn(user);
        when(user.getPassword()).thenReturn(USER_PASSWORD);
        when(user.getId()).thenReturn(USER_ID);
        when(user.getAccessRole()).thenReturn(ROLE_ADMIN);
        when(httpServletRequest.getSession(true)).thenReturn(httpSession);
        when(httpServletRequest.getParameter("login")).thenReturn(USER_LOGIN);
        when(httpServletRequest.getParameter("password")).thenReturn(USER_PASSWORD);

        String resultPage = authorizationCommand.execute(httpServletRequest, httpServletResponse);

        assertEquals(ADMIN_MENU_PAGE, resultPage);
    }

    @Test
    public void shouldExecuteGetUserOfficeCommandWhenRoleUser() {
        when(passwordEncoder.encode(any())).thenReturn(USER_PASSWORD);
        when(userDao.read(any())).thenReturn(user);
        when(user.getPassword()).thenReturn(USER_PASSWORD);
        when(user.getId()).thenReturn(USER_ID);
        when(user.getAccessRole()).thenReturn(ROLE_DRIVER);
        when(httpServletRequest.getSession(true)).thenReturn(httpSession);
        when(httpServletRequest.getParameter("login")).thenReturn(USER_LOGIN);
        when(httpServletRequest.getParameter("password")).thenReturn(USER_PASSWORD);

        authorizationCommand.execute(httpServletRequest, httpServletResponse);

        verify(getUserOfficeCommand).execute(httpServletRequest, httpServletResponse);
    }
}
