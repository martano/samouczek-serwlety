package com.martaexercises.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Map;

@WebFilter(servletNames = "parametersServlet")
public class BlockingFilter implements Filter {
    private static final String BLOCKED_PARAMETER = "blokuj";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        for(Map.Entry<String, String[]> parameters : req.getParameterMap().entrySet()) {
            for(String parameter : parameters.getValue()) {
                if(parameter.toLowerCase().equals(BLOCKED_PARAMETER)) {
                    resp.getWriter().write("<html><body><p>Blocking parameter detected</p></body></head>");
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy(){
    }
}
