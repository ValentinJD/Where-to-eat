package ru.wheretoeat.util;

import ru.wheretoeat.Profiles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbUtil {

    private static String url = "jdbc:postgresql://localhost:5432/postgres";
    private static String user = "user";
    private static String password = "password";

    private dbUtil() {
    }

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
                InputStream inputStream;
                if (Profiles.getActiveDbProfile().equals(Profiles.POSTGRES_DB)) {
                    inputStream = dbUtil.class.getClassLoader().getResourceAsStream("db/postgres.properties");
                    prop.load(inputStream);
                } else {
                    inputStream = dbUtil.class.getClassLoader().getResourceAsStream("db/hsqldb.properties");
                    prop.load(inputStream);
                }
                String driver = prop.getProperty("database.driver");
                String url = prop.getProperty("database.url");
                String user = prop.getProperty("database.user");
                String password = prop.getProperty("database.password");

                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }
}
