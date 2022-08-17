package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/dbtest";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "36350022qQ!";

    public static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection complete successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory(){
        return new Configuration()
                .addAnnotatedClass(User.class)
                .buildSessionFactory();
    }
}
