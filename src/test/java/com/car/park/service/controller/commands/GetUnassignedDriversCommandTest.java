package com.car.park.service.controller.commands;

import com.car.park.service.dao.UserDao;
import com.car.park.service.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.car.park.service.model.UserRole.ROLE_ADMIN;
import static com.car.park.service.model.UserRole.ROLE_DRIVER;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GetUnassignedDriversCommandTest {

    private static final String UNASSIGNED_DRIVERS_PAGE = "WEB-INF/jsp/admin-route-assign-driver-page.jsp";
    private static final String ROUTE_ID = "routeId";
    private static final String ASSIGNMENT_ID = "assignmentId";

    @InjectMocks
    private GetUnassignedDriversCommand getUnassignedDriversCommand;
    @Mock
    private UserDao userDao;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;

    @Test
    public void shouldSetRouteIdToRequest() {
        when(userDao.readAllWithoutAssignment()).thenReturn(emptyList());
        when(request.getParameter("routeId")).thenReturn(ROUTE_ID);

        getUnassignedDriversCommand.execute(request, response);

        verify(request).setAttribute("routeId", ROUTE_ID);
    }

    @Test
    public void shouldSetAssignmentIdToRequest() {
        when(userDao.readAllWithoutAssignment()).thenReturn(emptyList());
        when(request.getParameter("assignmentId")).thenReturn(ASSIGNMENT_ID);

        getUnassignedDriversCommand.execute(request, response);

        verify(request).setAttribute("assignmentId", ASSIGNMENT_ID);
    }

    @Test
    public void shouldSetUsersFilteredByDriverRoleToRequest() {
        User userAdmin = new User();
        User userDriver = new User();
        userAdmin.setAccessRole(ROLE_ADMIN);
        userDriver.setAccessRole(ROLE_DRIVER);
        when(userDao.readAllWithoutAssignment()).thenReturn(asList(userAdmin, userDriver));

        getUnassignedDriversCommand.execute(request, response);

        verify(request).setAttribute("users", singletonList(userDriver));
    }

    @Test
    public void shouldReturnUnassignedDriversPage() {
        when(userDao.readAllWithoutAssignment()).thenReturn(emptyList());

        String resultPage = getUnassignedDriversCommand.execute(request, response);

        assertEquals(UNASSIGNED_DRIVERS_PAGE, resultPage);
    }
}
