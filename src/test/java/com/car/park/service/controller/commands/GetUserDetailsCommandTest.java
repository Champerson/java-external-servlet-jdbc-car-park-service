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

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUserDetailsCommandTest {

    private static final String USER_INFO_PAGE = "WEB-INF/jsp/admin-user-details-page.jsp";

    private static final String ID = "3";
    private static final long ID_PARSED = 3;

    @InjectMocks
    GetUserDetailsCommand getUserDetailsCommand;
    @Mock
    private UserDao userDao;
    @Mock
    private AssignmentDao assignmentDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    User user;
    @Mock
    Assignment assignment;

    @Test
    public void shouldReturnUserInfoPage() {
        when(request.getParameter("userId")).thenReturn(ID);
        when(userDao.read(ID_PARSED)).thenReturn(user);

        String resultPage = getUserDetailsCommand.execute(request, response);

        assertEquals(USER_INFO_PAGE, resultPage);
    }

    @Test
    public void shouldSetUserToRequest() {
        when(request.getParameter("userId")).thenReturn(ID);
        when(userDao.read(ID_PARSED)).thenReturn(user);
        when(assignmentDao.readByDriverId(ID_PARSED)).thenReturn(assignment);

        getUserDetailsCommand.execute(request, response);

        verify(request).setAttribute("user", user);
    }
}
