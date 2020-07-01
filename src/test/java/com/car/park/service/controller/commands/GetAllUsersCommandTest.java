package com.car.park.service.controller.commands;

import com.car.park.service.dao.AssignmentDao;
import com.car.park.service.dao.UserDao;
import com.car.park.service.model.Assignment;
import com.car.park.service.model.Route;
import com.car.park.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetAllUsersCommandTest {

    private static final String ALL_USERS_PAGE = "WEB-INF/jsp/admin-user-all-page.jsp";

    private static final long ID = 3;

    @InjectMocks
    GetAllUsersCommand getAllUsersCommand;
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
    public void shouldReturnAllUsersPage() {
        when(userDao.readAll()).thenReturn(singletonList(user));
        when(user.getId()).thenReturn(ID);
        when(assignmentDao.readByRouteId(ID)).thenReturn(singletonList(assignment));

        String resultPage = getAllUsersCommand.execute(request, response);

        assertEquals(ALL_USERS_PAGE, resultPage);
    }

    @Test
    public void shouldSetListOfAllUsersToRequest() {
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userDao.readAll()).thenReturn(singletonList(user));
        when(user.getId()).thenReturn(ID);
        when(assignmentDao.readByRouteId(ID)).thenReturn(singletonList(assignment));

        getAllUsersCommand.execute(request, response);

        verify(request).setAttribute("users", users);
    }
}
