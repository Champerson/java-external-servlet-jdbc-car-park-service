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

import static java.lang.String.valueOf;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateAssignmentForDriverCommandTest {

    private static final long ID = 2;

    @InjectMocks
    CreateAssignmentForDriverCommand createAssignmentForDriverCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private AssignmentDao assignmentDao;
    @Mock
    private TransactionManager transactionManager;
    @Mock
    private Command getRouteDetailsCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    Assignment assignment;

    @Test
    public void shouldExecuteGetRouteDetailsCommand() {
        when(request.getParameter("userId")).thenReturn(valueOf(ID));
        when(request.getParameter("assignmentId")).thenReturn(valueOf(ID));
        when(assignmentDao.read(ID)).thenReturn(assignment);

        createAssignmentForDriverCommand.execute(request, response);

        verify(getRouteDetailsCommand).execute(request, response);
    }

    @Test
    public void shouldUpdateAssignmentAndCommitTransaction() {
        when(request.getParameter("userId")).thenReturn(valueOf(ID));
        when(request.getParameter("assignmentId")).thenReturn(valueOf(ID));
        when(assignmentDao.read(ID)).thenReturn(assignment);

        createAssignmentForDriverCommand.execute(request, response);

        verify(assignmentDao).update(assignment);
        verify(transactionManager).commit();
    }
}