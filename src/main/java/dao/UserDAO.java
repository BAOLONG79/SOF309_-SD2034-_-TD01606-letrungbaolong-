package com.coffeepoly.dao;

import com.coffeepoly.model.User;
import com.coffeepoly.util.DB;
import com.coffeepoly.util.PasswordUtil;
import java.sql.*;

public class UserDAO {
    public boolean register(String name, String email, String phone, String password) throws Exception {
        String sql = "INSERT INTO Users(full_name,email,phone,password,role) VALUES(?,?,?,?, 'CUSTOMER')";
        try (Connection con=DB.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,name); ps.setString(2,email); ps.setString(3,phone);
            ps.setString(4,PasswordUtil.hash(password));
            return ps.executeUpdate()==1;
        }
    }

    public User login(String email, String password) throws Exception {
        String sql="SELECT user_id,full_name,email,phone,password,role FROM Users WHERE email=?";
        try (Connection con=DB.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,email);
            try(ResultSet rs=ps.executeQuery()) {
                if(rs.next() && PasswordUtil.matches(password,rs.getString("password"))) {
                    return new User(rs.getInt("user_id"),rs.getString("full_name"),
                        rs.getString("email"),rs.getString("phone"),rs.getString("role"));
                }
            }
        }
        return null;
    }
}
