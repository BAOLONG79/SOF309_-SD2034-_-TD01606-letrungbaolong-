package com.coffeepoly.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DB {
    private static final Properties CONFIG = new Properties();

    static {
        try (InputStream in = DB.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (in == null) throw new IllegalStateException("Khong tim thay db.properties");
            CONFIG.load(in);
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + CONFIG.getProperty("server", "localhost")
            + ":" + CONFIG.getProperty("port", "1433")
            + ";databaseName=" + CONFIG.getProperty("database", "CoffeePolyDB")
            + ";encrypt=" + CONFIG.getProperty("encrypt", "true")
            + ";trustServerCertificate=" + CONFIG.getProperty("trustServerCertificate", "true");
        return DriverManager.getConnection(url,
            CONFIG.getProperty("username"), CONFIG.getProperty("password"));
    }
}
