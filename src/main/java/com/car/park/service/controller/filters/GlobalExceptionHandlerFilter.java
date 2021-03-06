package com.car.park.service.controller.filters;

import com.car.park.service.dao.exceptions.UncheckedSQLException;
import com.car.park.service.dao.support.TransactionManager;
import org.apache.log4j.Logger;

import javax.servlet.*;

/**
 * This filter handles all exceptions for all requests.
 * In case of exception it rollbacks transaction, closes connection, logs error and redirects to an error page.
 */
public class GlobalExceptionHandlerFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(GlobalExceptionHandlerFilter.class);
    private static final String ENCODING = "UTF-8";
    private static final String ERROR_PAGE = "WEB-INF/jsp/error-page.jsp";

    private final TransactionManager transactionManager = TransactionManager.getInstance();

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) {
        try {
            request.setCharacterEncoding(ENCODING);
            filterChain.doFilter(request, response);
        } catch (UncheckedSQLException e) {
            handleException(e);
            forwardErrorRequest(request, response, "error.database.failure");
        } catch (Throwable e) {
            handleException(e);
            forwardErrorRequest(request, response, "error.unknown");
        } finally {
            transactionManager.closeConnection();
        }
    }

    private void handleException(Throwable e) {
        LOG.error(e.getMessage(), e);
        transactionManager.rollback();
    }

    private void forwardErrorRequest(ServletRequest request, ServletResponse response, String errorMessageKey) {
        try {
            request.setAttribute("errorMessage", errorMessageKey);
            request.getRequestDispatcher(ERROR_PAGE).forward(request, response);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void destroy() {

    }
}
