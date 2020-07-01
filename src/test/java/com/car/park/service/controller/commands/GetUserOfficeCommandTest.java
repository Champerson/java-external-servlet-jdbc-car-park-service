package com.car.park.service.controller.commands;

import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
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

import static com.car.park.service.controller.CommandMapping.Commands.GET_USER_OFFICE;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUserOfficeCommandTest {

    private static final String USER_OFFICE_PAGE = "WEB-INF/jsp/user-office-page.jsp";

    private static final long ID = 3;
    private static final String COMMAND = "edit_user";

    @InjectMocks
    GetUserOfficeCommand getUserOfficeCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private AssignmentDao assignmentDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    HttpSession session;
    @Mock
    User user;
    @Mock
    Assignment assignment;

    @Test
    public void shouldReturnUserOfficePage() {
        when(request.getParameter("command")).thenReturn(COMMAND);
        when(assignmentDao.readByDriverId(ID)).thenReturn(assignment);
        when(request.getParameter("locale")).thenReturn(null);
        when(session.getAttribute("userId")).thenReturn(ID);
        when(request.getSession()).thenReturn(session);
        when(userDao.read(ID)).thenReturn(user);
        when(user.getId()).thenReturn(ID);

        String resultPage = getUserOfficeCommand.execute(request, response);

        assertEquals(USER_OFFICE_PAGE, resultPage);
    }

    @Test
    public void shouldSetUserAndCommandToRequest() {
        when(request.getParameter("command")).thenReturn(COMMAND);
        when(assignmentDao.readByDriverId(ID)).thenReturn(assignment);
        when(request.getParameter("locale")).thenReturn(null);
        when(session.getAttribute("userId")).thenReturn(ID);
        when(request.getSession()).thenReturn(session);
        when(userDao.read(ID)).thenReturn(user);
        when(user.getId()).thenReturn(ID);

        getUserOfficeCommand.execute(request, response);

        verify(request).setAttribute("user", user);
        verify(request).setAttribute("command", GET_USER_OFFICE.name());
    }
}
