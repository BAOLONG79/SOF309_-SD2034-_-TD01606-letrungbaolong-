package com.coffeepoly.util;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebListener
public class AdminInitializer implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        String email = "admin@coffeepoly.com";
        String checkSql = "SELECT user_id FROM Users WHERE email=?";
        String insertSql = "INSERT INTO Users(full_name,email,phone,password,role) VALUES(?,?,?,?,?)";
        try (Connection con = DB.getConnection();
             PreparedStatement check = con.prepareStatement(checkSql)) {
            check.setString(1, email);
            try (ResultSet rs = check.executeQuery()) {
                if (rs.next()) {
                    event.getServletContext().log("CoffeePoly Admin da ton tai.");
                    return;
                }
            }
            try (PreparedStatement ps = con.prepareStatement(insertSql)) {
                ps.setString(1, "CoffeePoly Administrator");
                ps.setString(2, email);
                ps.setString(3, "0900000000");
                ps.setString(4, PasswordUtil.hash("Admin@123"));
                ps.setString(5, "ADMIN");
                ps.executeUpdate();
            }
            event.getServletContext().log("Da tao tai khoan Admin mac dinh.");
        } catch (Exception e) {
            event.getServletContext().log("Khong the khoi tao Admin. Kiem tra database.", e);
        }
    }
}
