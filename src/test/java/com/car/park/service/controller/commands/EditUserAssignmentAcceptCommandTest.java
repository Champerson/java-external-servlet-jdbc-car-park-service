package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;
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
public class EditUserAssignmentAcceptCommandTest {

    private static final long ID = 3;

    @InjectMocks
    EditUserAssignmentAcceptCommand editUserAssignmentAcceptCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private AssignmentDao assignmentDao;
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
    @Mock
    Assignment assignment;

    @Test
    public void shouldExecuteGetUserOfficeCommand() {
        when(user.getId()).thenReturn(ID);
        when(userDao.read(ID)).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn(ID);
        when(assignmentDao.readByDriverId(ID)).thenReturn(assignment);

        editUserAssignmentAcceptCommand.execute(request, response);

        verify(getUserOfficeCommand).execute(request, response);
    }

    @Test
    public void shouldUpdateAssignmentAndCommitTransaction() {
        when(user.getId()).thenReturn(ID);
        when(userDao.read(ID)).thenReturn(user);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("userId")).thenReturn(ID);
        when(assignmentDao.readByDriverId(ID)).thenReturn(assignment);

        editUserAssignmentAcceptCommand.execute(request, response);

        verify(assignmentDao).update(assignment);
        verify(transactionManager).commit();
    }
}
