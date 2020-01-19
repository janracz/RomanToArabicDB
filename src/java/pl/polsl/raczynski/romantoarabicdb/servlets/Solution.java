/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.raczynski.romantoarabicdb.model.*;
import pl.polsl.raczynski.romantoarabicdb.dbtools.UserHistory;

/**
 * Servlet that shows the solution of conversion
 *
 * @author Jasiek
 * @version 1.0
 */
public class Solution extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Gets a model controller from session or creates one if not
     * available. Solves Knight Tour Problem and displays result for first field
     * given by user.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username") || cookie.getName().equals("password")) {
                    cookie.setMaxAge(600);
                    response.addCookie(cookie);
                }
            }
        }

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Finding solution</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Convert Roman number to Arabic number</h1>");
            out.println("<br>");
            out.println("<form action=\"Solution\" method=\"GET\">");
            out.println("<p>Number to convert: <input type=text size=20 name=field></p>");
            out.println("<input type=\"submit\" value=\"Convert\"/>");
            out.println("</form>");
            out.println("<br>");

            boolean correctParameter = true;
            String wrongParameterMessage = "";
            
            RomanNumber valueToConvert = new RomanNumber();
            ArabicNumber convertedValue = new ArabicNumber();
            Converter conversionAlgorithm = new Converter();
            try {
                valueToConvert.setRomanNumber(request.getParameter("field").toUpperCase());
            } catch (NumberException e) {
                correctParameter = false;
                wrongParameterMessage = e.getMessage();
            }

            if (correctParameter) {
                convertedValue.setArabicNumber(conversionAlgorithm.conversionFromRomanToArabic(valueToConvert.
                        getRomanNumber()));
                
                
                out.println("<hl>Conversion result from number: <font color=MediumVioletRed>" + 
                        request.getParameter("field").toUpperCase() + "</font></hl>");

                out.println("<p>Result of conversion: <font color=LimeGreen>" + convertedValue.getArabicNumber() +
                        "</font></p>");
                
                UserHistory addHistory = new UserHistory();
                addHistory.createTable();
                addHistory.setHistory(cookies[0].getValue(), valueToConvert.getRomanNumber(),
                        convertedValue.getArabicNumber());
                
            } else {
                out.println("<div><font color=red>" + wrongParameterMessage + "</font></div>");
            }
            out.println("<br>");
            out.println("<br>");
            out.println("<form action=\"History\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"Check history\"/>");
            out.println("</form>");
            out.println("<br>");
            out.println("<form action=\"Logout\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"Log out\"/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
