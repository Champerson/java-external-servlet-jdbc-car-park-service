package com.car.park.service.controller.commands;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LogoutCommandTest {

    private static final String INDEX_PAGE = "index.jsp";

    @InjectMocks
    LogoutCommand logoutCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    HttpSession session;

    @Test
    public void shouldReturnIndexPage() {
        when(request.getSession()).thenReturn(session);

        String resultPage = logoutCommand.execute(request, response);

        assertEquals(INDEX_PAGE, resultPage);
    }

    @Test
    public void shouldInvalidateSession() {
        when(request.getSession()).thenReturn(session);

        logoutCommand.execute(request, response);

        verify(session).invalidate();
    }
}
