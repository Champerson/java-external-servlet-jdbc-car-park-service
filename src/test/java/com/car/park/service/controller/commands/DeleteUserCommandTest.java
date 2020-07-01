package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.dao.support.TransactionManager;
import com.car.park.service.model.Assignment;
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
public class DeleteUserCommandTest {

    private static final String ID = "2";
    private static final long ID_PARSED = 2;

    @InjectMocks
    DeleteUserCommand deleteUserCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private AssignmentDao assignmentDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getAllUsersCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    Assignment assignment;

    @Test
    public void shouldExecuteGetAllUsersCommand() {
        when(request.getParameter("userId")).thenReturn(ID);
        when(assignmentDao.readByDriverId(ID_PARSED)).thenReturn(null);

        deleteUserCommand.execute(request, response);

        verify(getAllUsersCommand).execute(request, response);
    }

    @Test
    public void shouldDeleteUserAndCommitTransactionWithAssignmentUpdate() {
        when(request.getParameter("userId")).thenReturn(ID);
        when(assignmentDao.readByDriverId(ID_PARSED)).thenReturn(assignment);

        deleteUserCommand.execute(request, response);

        verify(assignmentDao).update(assignment);
        verify(userDao).delete(ID_PARSED);
        verify(transactionManager).commit();
    }

    @Test
    public void shouldDeleteUserAndCommitTransactionWithoutAssignmentUpdate() {
        when(request.getParameter("userId")).thenReturn(ID);
        when(assignmentDao.readByDriverId(ID_PARSED)).thenReturn(null);

        deleteUserCommand.execute(request, response);

        verify(userDao).delete(ID_PARSED);
        verify(transactionManager).commit();
    }
}
