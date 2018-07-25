package com.martaexercises.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/servlet", name = "parametersServlet")
public class ParametersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException {
        PrintWriter responseWriter = resp.getWriter();
        responseWriter.write("<html><body>");
        responseWriter.write("<p>Request does not contain blocking parameter: </p>");
        for(Map.Entry<String, String[]> parameters : req.getParameterMap().entrySet()) {
            for(String parameter : parameters.getValue()) {
                responseWriter.write("<p>" + parameter + "</p>");
            }
        }
        responseWriter.write("</body></html");
    }
}
