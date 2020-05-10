package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditUserPasswordCommandTest {

    private static final String OLD_PASSWORD = "oldPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String OLD_PASSWORD_ENCODED = "oldPasswordEncoded";
    private static final String NEW_PASSWORD_ENCODED = "newPasswordEncoded";
    private static final String DIFFERENT_PASSWORD_ENCODED = "differentPasswordEncoded";
    private static final long USER_ID = 1;

    @InjectMocks
    private EditUserPasswordCommand editUserPasswordCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private TransactionManager transactionManager;
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
    public void shouldUpdateUserWithNewEncodedPasswordAndCommitTransactionWhenOldPasswordMatch() {
        when(httpServletRequest.getParameter("oldPassword")).thenReturn(OLD_PASSWORD);
        when(httpServletRequest.getParameter("newPassword")).thenReturn(NEW_PASSWORD);
        when(passwordEncoder.encode(OLD_PASSWORD)).thenReturn(OLD_PASSWORD_ENCODED);
        when(passwordEncoder.encode(NEW_PASSWORD)).thenReturn(NEW_PASSWORD_ENCODED);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userId")).thenReturn(USER_ID);
        when(userDao.read(USER_ID)).thenReturn(user);
        when(user.getPassword()).thenReturn(OLD_PASSWORD_ENCODED);

        editUserPasswordCommand.execute(httpServletRequest, httpServletResponse);

        verify(user).setPassword(NEW_PASSWORD_ENCODED);
        verify(userDao).update(user);
        verify(transactionManager).commit();
    }

    @Test
    public void shouldExecuteGetUserOfficeCommandWhenPasswordUpdated() {
        when(httpServletRequest.getParameter("oldPassword")).thenReturn(OLD_PASSWORD);
        when(httpServletRequest.getParameter("newPassword")).thenReturn(NEW_PASSWORD);
        when(passwordEncoder.encode(OLD_PASSWORD)).thenReturn(OLD_PASSWORD_ENCODED);
        when(passwordEncoder.encode(NEW_PASSWORD)).thenReturn(NEW_PASSWORD_ENCODED);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userId")).thenReturn(USER_ID);
        when(userDao.read(USER_ID)).thenReturn(user);
        when(user.getPassword()).thenReturn(OLD_PASSWORD_ENCODED);

        editUserPasswordCommand.execute(httpServletRequest, httpServletResponse);

        verify(getUserOfficeCommand).execute(httpServletRequest, httpServletResponse);
    }

    @Test
    public void shouldExecuteGetUserOfficeCommandWhenPasswordNotUpdated() {
        when(httpServletRequest.getParameter("oldPassword")).thenReturn(OLD_PASSWORD);
        when(passwordEncoder.encode(OLD_PASSWORD)).thenReturn(OLD_PASSWORD_ENCODED);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpSession.getAttribute("userId")).thenReturn(USER_ID);
        when(userDao.read(USER_ID)).thenReturn(user);
        when(user.getPassword()).thenReturn(DIFFERENT_PASSWORD_ENCODED);

        editUserPasswordCommand.execute(httpServletRequest, httpServletResponse);

        verify(getUserOfficeCommand).execute(httpServletRequest, httpServletResponse);
    }
}
