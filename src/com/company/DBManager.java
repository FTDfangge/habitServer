package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


public class DBManager extends HttpServlet{
    ServletConfig config;
    private static String username;
    private static String password;
    private static String url;
    private static Connection connection;

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        this.config = config;
        username = config.getInitParameter("DBUsername");
        password = config.getInitParameter("DBPassword");
        url = config.getInitParameter("ConnectionURL");
    }

    //连接数据库
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException | SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
