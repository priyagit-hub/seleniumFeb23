package com.envision.automation.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySqlDbUtils {

    //step1 make a connection


    public Connection getConnection (String dbUrl,String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // register the driver to use for db ....google it
        Connection connection = DriverManager.getConnection(dbUrl,username,password);
        // step2 create statement
        Statement stmt= connection.createStatement();
        //step3 execute
        stmt.execute("");

        return null;
    }
}
