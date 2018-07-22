package com.martaexercises.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

@WebServlet("/serwlet")
public class ParamAndSessionServlet extends HttpServlet {
    ///serwlet?parametr=123&inny-parametr=abc&test=-3
    private static int parametersSum;
    private static int totalSum;
    private static final String PARAM_SUM_FROM_ALL_VISITS = "sessionParametersSum";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseWriter = resp.getWriter();
        responseWriter.write("<html><body>");
        calculateParametersSum(req);
        getSession(req);
        responseWriter.write("<p>requestSum: " + parametersSum + "</p>");
        responseWriter.write("<p>totalSum: " + totalSum + "</p>");
        responseWriter.write("</body></html");
    }

    private static void calculateParametersSum(HttpServletRequest req) {
        parametersSum = 0;
        for(Map.Entry<String, String[]> parameters : req.getParameterMap().entrySet()) {
            for(String parameter : parameters.getValue()) {
                parametersSum += convertStringToInt(parameter);
            }
        }
    }

    private static int convertStringToInt(String parameter) {
        int convertedValue;
        try {
            convertedValue = Integer.parseInt(parameter);
        } catch (NumberFormatException | NullPointerException nfe) {
            //Parameter is not a number and can not be used to calculate sum
            convertedValue = 0;
        }
        return convertedValue;
    }

    private static void getSession(HttpServletRequest req) {
        HttpSession session = req.getSession();
        calculateParamSumForSession(session);
    }

    private static void calculateParamSumForSession(HttpSession session) {
        Object sessionSum = session.getAttribute(PARAM_SUM_FROM_ALL_VISITS);
        if(sessionSum != null) {
            totalSum = (Integer) sessionSum + parametersSum;
        } else {
            totalSum = parametersSum;
        }
        session.setAttribute(PARAM_SUM_FROM_ALL_VISITS, totalSum);
    }
}
