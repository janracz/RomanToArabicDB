/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.dbtools;

import java.sql.*;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * class that manages History table
 *
 * @author Jasiek
 * @version 1.0
 */

public class UserHistory {
    
    /**
     * Constructor of class
     */
    public void UserHistory(){
    }

    
    /**
     * A methode to create History table
     */
    public void createTable() {
        
        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return;
        }

        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RomanToArabicDB", "Roman", "Arabic")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("CREATE TABLE History "
                    + "(username VARCHAR(50), "
                    + "roman VARCHAR(50), arabic INTEGER )");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }

    
    /**
     * Methode to get a list with history from History table
     * 
     * @param username username that want to get the history
     * @return the list with history
     */
    public List<String> getHistory(String username) {
        List<String> history = new ArrayList<>();
                
        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
        }
        
        
        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RomanToArabicDB", "Roman", "Arabic")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM History WHERE username = '" + username + "'");
            // Przeglądamy otrzymane wyniki
            if (rs.next() == false) {
                System.out.println("ResultSet in empty in Java"); 
                rs.close();
                return null;
            } else {
                do {
                    String historyElement = "<p>Roman number: " + rs.getString("roman") + "</p>\n";
                    historyElement += ("<p>Arabic Number: " + rs.getInt("arabic") + "</p>\n");
                    history.add(historyElement);
                } while(rs.next());
                rs.close();
                return history;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            return null;
        }
    }
    
    
    /**
     * Methode to set history in History table
     * 
     * @param username username that sets history
     * @param romanNumber roman number to set
     * @param arabicNumber arabic number to set
     */
    public void setHistory(String username, String romanNumber, int arabicNumber) {
        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
        }
        
        
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RomanToArabicDB", "Roman", "Arabic")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO History VALUES ('" + username + "', '" + romanNumber + 
                    "', " + arabicNumber +")" );
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
        
    }
    
}