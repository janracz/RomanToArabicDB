/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import pl.polsl.raczynski.romantoarabicdb.dbtools.SelectUsernamePass;

/**
 * Servlet that shows the solution of conversion
 *
 * @author Jasiek
 * @version 1.0
 */
public class Signup extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods. Handles the sign up request.
     *
     * @param request servlet request.
     * @param response servlet response.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        boolean userAlreadyExists = false;
        boolean accountCreated = false;

        boolean nullParameter = false;
        boolean emptyParameter = false;
        boolean spacesInParameter = false;

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        SelectUsernamePass addUser = new SelectUsernamePass();
        addUser.createTable();

        if (username == null || password == null) {
            nullParameter = true;
        } else if (username.contains(" ") || password.contains(" ")) {
            spacesInParameter = true;
        } else if (username.isEmpty() || password.isEmpty()) {
            emptyParameter = true;
        }

        if (!nullParameter && !emptyParameter && !spacesInParameter) {
            accountCreated = addUser.addUser(username, password);
            userAlreadyExists = !accountCreated;
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Signup</title>");
            out.println("</head>");
            out.println("<body>");

            if (!accountCreated) {
                out.println("<h1>Sign up</h1>");

                if (userAlreadyExists) {
                    out.println("<div><font color=red>Username is occupied!</font></div>");
                } else if (emptyParameter) {
                    out.println("<div><font color=red>Username and password cannot be empty!</font></div>");
                } else if (spacesInParameter) {
                    out.println("<div><font color=red>There cannot be whitespaces in username and password!</font></div>");
                }
                out.println("<form action=\"Signup\" method=\"GET\">");
                out.println("<p>Username <input type=text size=20 name=username></p>");
                out.println("<p>Password <input type=text size=20 name=password></p>");
                out.println("<input type=\"submit\" value=\"Add user\" />");
                out.println("</form>");
                out.println("<br>");
                out.println("<form action=\"index.html\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"back to starting page\"/>");
                out.println("</form>");
            } else {

                out.println("<h1>Your account was created successfully!</h1>");
                out.println("<p>Now you can log in.</p>");
                out.println("<form action=\"index.html\" method=\"GET\">");
                out.println("<input type=\"submit\" value=\"back to starting page\"/>");
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
