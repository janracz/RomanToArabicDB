/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.polsl.raczynski.romantoarabicdb.dbtools;

import java.sql.*;

/**
 * class that manages PassBase table
 *
 * @author Jasiek
 * @version 1.0s
 */
public class SelectUsernamePass {
    
    
    /**
     * Constructor of class
     */
    public void SelectUsernamePass() {
    }
    
    /**
     * Methode to create PassBase table
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
            statement.executeUpdate("CREATE TABLE PassBase "
                    + "(username VARCHAR(50), "
                    + "pass VARCHAR(50) )");
            System.out.println("Table created");
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
        }
    }
    
    
    
    /**
     * A methode that selects username and his password from PassBase table
     * 
     * @param username username to search
     * @param password password to search
     * @return 
     */
    public boolean selectData(String username, String password) {

        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return false;
        }

        // make a connection to DB
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RomanToArabicDB", "Roman", "Arabic")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT pass FROM PassBase WHERE username = '" + username + "'");
            // Przeglądamy otrzymane wyniki
            if (rs.next() == false) {
                System.out.println("ResultSet in empty in Java"); 
                rs.close();
                return false;
            } else {
                rs.close();
                return true;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            return false;
        }
    }
    
    
    /**
     * Methode to add user and password to PassBase table
     * 
     * @param username username to add
     * @param password password to add
     * @return 
     */
    public boolean addUser(String username, String password) {
        try {
            // loading the JDBC driver
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException cnfe) {
            System.err.println(cnfe.getMessage());
            return false;
        }
        
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/RomanToArabicDB", "Roman", "Arabic")) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM PassBase WHERE username = '" + username + "'");
            // Przeglądamy otrzymane wyniki
            if (rs.next() == false) {
                statement.execute("INSERT INTO PassBase VALUES ('" + username + "', '" + password + "')" );
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }
        } catch (SQLException sqle) {
            System.err.println(sqle.getMessage());
            return false;
        }
    }
}
