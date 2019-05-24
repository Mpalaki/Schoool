/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Makis
 */
public class dbutils {
            private static final String USERNAME = "  ";
            private static final String PASS = " ";
            private static final String MYSQLURL = "jdbc:mysql://localhost:3306/schoool?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowMultiQueries=true";
            
    public static Connection createConnection(){  
            Connection conn= null;     
            try {
            conn = DriverManager.getConnection(MYSQLURL,USERNAME,PASS);
            return conn;
            
        } catch (SQLException ex) {
            Logger.getLogger(dbutils.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;}
    
    
}
