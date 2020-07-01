package com.car.park.service.controller.commands;


import com.car.park.service.controller.Command;
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

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EditUserRoleCommandTest {

    private static final String  ID = "3";
    private static final long ID_PARSED = 3;
    private static final String USER_ROLE = "ROLE_ADMIN";

    @InjectMocks
    EditUserRoleCommand editUserRoleCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getUserDetailsCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    User user;

    @Test
    public void shouldExecuteGetUserDetailsCommand() {
        when(request.getParameter("accessRole")).thenReturn(USER_ROLE);
        when(request.getParameter("userId")).thenReturn(ID);
        when(userDao.read(ID_PARSED)).thenReturn(user);

        editUserRoleCommand.execute(request, response);

        verify(getUserDetailsCommand).execute(request, response);
    }

    @Test
    public void shouldUpdateUserAndCommitTransaction() {
        when(request.getParameter("accessRole")).thenReturn(USER_ROLE);
        when(request.getParameter("userId")).thenReturn(ID);
        when(userDao.read(ID_PARSED)).thenReturn(user);

        editUserRoleCommand.execute(request, response);

        verify(userDao).update(user);
        verify(transactionManager).commit();
    }
}
