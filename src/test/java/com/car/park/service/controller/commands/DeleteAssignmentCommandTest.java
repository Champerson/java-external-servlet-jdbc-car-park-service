package com.car.park.service.controller.commands;

import com.car.park.service.controller.Command;
import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.support.TransactionManager;
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
public class DeleteAssignmentCommandTest {

    private static final String ID = "3";
    private static final long ID_PARSED = 3;

    @InjectMocks
    DeleteAssignmentCommand deleteAssignmentCommand;
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

    @Test
    public void shouldExecuteGetRouteDetailsCommand() {
        when(request.getParameter("assignmentId")).thenReturn(ID);

        deleteAssignmentCommand.execute(request, response);

        verify(getRouteDetailsCommand).execute(request, response);
    }

    @Test
    public void shouldDeleteAssignmentAndCommitTransaction() {
        when(request.getParameter("assignmentId")).thenReturn(ID);

        deleteAssignmentCommand.execute(request, response);

        verify(assignmentDao).delete(ID_PARSED);
        verify(transactionManager).commit();
    }
}
