package com.martaexercises.servlets;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.util.Date;

@WebListener
public class AddContexAttrServletListener implements ServletContextAttributeListener {
    private static final String PARAMETER_SUFIX = ".when";

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        if(event.getName().endsWith(PARAMETER_SUFIX)) {
            return;
        }
        String newAttrName = event.getName() + PARAMETER_SUFIX;
        event.getServletContext().setAttribute(newAttrName, new Date());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {

    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {

    }
}
