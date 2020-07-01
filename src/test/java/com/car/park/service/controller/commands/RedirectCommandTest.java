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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RedirectCommandTest {

    private static final String PAGE_TO_REDIRECT = "index.jsp";

    @InjectMocks
    RedirectCommand redirectCommand;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    HttpSession session;

    @Test
    public void shouldReturnPageToRedirect() {
        when(request.getParameter("pageToRedirect")).thenReturn(PAGE_TO_REDIRECT);
        when(request.getSession()).thenReturn(session);

        String resultPage = redirectCommand.execute(request, response);

        assertEquals(PAGE_TO_REDIRECT, resultPage);
    }
}
