package com.martaexercises.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/addcontextattr")
public class AddContexAttrServlet extends HttpServlet {
    private static ServletContext contex;
    private static Map<String, String> contexAttrNamesAndValues;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initContext(req);
        getParametersAndAddToAttributes(req);
        getContextAttrNamesAndValues(req);
        generateHtmlWeb(resp);
    }

    private static void initContext(HttpServletRequest req) {
        contex = req.getServletContext();
    }

    private static void getParametersAndAddToAttributes(HttpServletRequest req) {
        for(Map.Entry<String, String[]> parameters : req.getParameterMap().entrySet()) {
            contex.setAttribute(parameters.getKey(), parameters.getValue()[0]);
        }
    }

    private static void getContextAttrNamesAndValues(HttpServletRequest req) {
        contexAttrNamesAndValues = new HashMap<>();
        Enumeration<String> attributeNames = contex.getAttributeNames();
        while(attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            String attributeValue = contex.getAttribute(attributeName).toString();
            contexAttrNamesAndValues.put(attributeName, attributeValue);
        }
    }

    private static void generateHtmlWeb(HttpServletResponse resp) throws IOException{
        PrintWriter writer = resp.getWriter();
        writer.write("<html><body>");
        for(Map.Entry<String, String> entry : contexAttrNamesAndValues.entrySet()){
            writer.write("<p>" + entry.getKey() + ": " + entry.getValue() + "</p>");
        }
        writer.write("</body></html>");
    }
}
