package com.car.park.service.controller.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * This filter is responsible for localization, it sets locale obtained from user to session,
 * in case no locale provided it sets default 'en' locale.
 */
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
            passParametersToNextPage(request);
            session.setAttribute("locale", localeFromRequest);
        } else if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", DEFAULT_LOCALE);
        }
    }

    private void passParametersToNextPage(HttpServletRequest request) {
        for (Map.Entry<String, String []> parameter : request.getParameterMap().entrySet()) {
            String[] parameterValue = parameter.getValue();
            if (parameterValue.length != 0) {
                request.setAttribute(parameter.getKey(), parameterValue[0]);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
