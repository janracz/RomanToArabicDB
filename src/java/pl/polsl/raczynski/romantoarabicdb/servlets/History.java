/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.polsl.raczynski.romantoarabicdb.dbtools.UserHistory;

/**
 * Servlet that shows history from database
 *
 * @author Jasiek
 * @version 1.0
 */
public class History extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Prints knight's next moves from solved problem.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<String> history = null;
        UserHistory getHistory = new UserHistory();

        getHistory.createTable();


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
        
        history = getHistory.getHistory(cookies[0].getValue());

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>History of conversion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>History of conversion</h1>");
            out.println("<br>");
            out.println("<form action=\"Solution\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"Convert Roman number\" />");
            out.println("</form>");
            out.println("<br>");
            out.println("<form action=\"Logout\" method=\"GET\">");
            out.println("<input type=\"submit\" value=\"Log out\" />");
            out.println("</form>");
            out.println("<br>");
            out.println("<br>");

            if (history == null || history.isEmpty()) {
                out.println("<p>History is empty!</p>");
            } else {
                Collections.reverse(history);
                for (String s : history) {
                    out.println("<hr>");
                    out.print(s);
                }
            }
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
