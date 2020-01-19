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

import pl.polsl.raczynski.romantoarabicdb.dbtools.SelectUsernamePass;

/**
 * Servlet to login to service
 *
 * @author Jasiek
 * @version 1.0
 */
public class Login extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Allows to log in and checks if login data are correct. If data
     * are correct it redirects to menu. Otherwise, informs about it and
     * displays login form.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SelectUsernamePass checkUserPass = new SelectUsernamePass();
        checkUserPass.createTable();


        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Start</title>");
            out.println("</head>");
            out.println("<body>");

            if (username == null || password == null || username.isEmpty() || password.isEmpty()  || !checkUserPass.selectData(username, password)) {
                out.println("<h1>Welcome to Roman to Arabic conversion!</h1>");
                out.println("<hl><font color=red>You must log in to continue!</font></hl>");
                out.println("<form action=\"Login\" method=\"GET\">");
                out.println("<p>Username <input type=text size=20 name=username></p>");
                out.println("<p>Password <input type=password size=20 name=password></p>");
                out.println("<input type=\"submit\" value=\"Log in\" />");
                out.println("</form>");
            } else {
                Cookie cookie = new Cookie("user", username);
                cookie.setMaxAge(600);
                response.addCookie(cookie);
                cookie = new Cookie("password", password);
                cookie.setMaxAge(600);
                response.addCookie(cookie);

                out.println("<hl>What would you like to do?</hl>");
                out.println("<br>");
                out.println("<br>");
                out.println("<form action=\"History\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Check history\" />");
                out.println("</form>");
                out.println("<br>");
                out.println("<form action=\"Solution\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Convert Roman number\" />");
                out.println("</form>");
                out.println("<br>");
                out.println("<form action=\"Logout\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"Log out\" />");
                out.println("</form>");
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
