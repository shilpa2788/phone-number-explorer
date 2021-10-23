package au.com.belong.phonenumberexplorer.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;


@Component
public class RequestFilter implements Filter {

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        try {
            RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
            String body = requestWrapper.getBody();
            ((HttpServletRequest) request).getSession().setAttribute("body", body);
            ((HttpServletRequest) request).getSession().setAttribute("username",
                    ((HttpServletRequest) request).getRemoteUser());

            final String mdcData = String.format("[requestId:%s] ", requestId());
            MDC.put("mdcData", mdcData); //Referenced from logging configuration.
            chain.doFilter(requestWrapper, response);
        } finally {
            MDC.clear();
        }
    }

    private String requestId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void init(final FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}

