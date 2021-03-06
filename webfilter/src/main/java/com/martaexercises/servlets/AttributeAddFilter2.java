package com.martaexercises.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(servletNames = "attrDisplayServlet")
public class AttributeAddFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setAttribute("AttributeFromFilter2", "secondAttribute");
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy(){
    }
}
