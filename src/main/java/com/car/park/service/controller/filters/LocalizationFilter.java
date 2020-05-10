package com.car.park.service.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LocalizationFilter implements Filter {

    private static final String RESPONSE_CONTENT_TYPE = "text/html;charset=UTF-8";
    private static final String DEFAULT_LOCALE = "en_EN";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        setLocaleToSession((HttpServletRequest) request);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        filterChain.doFilter(request, response);
    }

    private void setLocaleToSession(HttpServletRequest request) {
        String localeFromRequest = request.getParameter("locale");
        HttpSession session = request.getSession(true);
        if (localeFromRequest != null) {
            session.setAttribute("locale", localeFromRequest);
        } else if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", DEFAULT_LOCALE);
        }
    }

    @Override
    public void destroy() {

    }
}
