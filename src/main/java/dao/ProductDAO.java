package com.coffeepoly.dao;

import com.coffeepoly.model.Product;
import com.coffeepoly.util.DB;
import java.sql.*;
import java.util.*;

public class ProductDAO {
    private Product map(ResultSet rs) throws SQLException {
        return new Product(rs.getInt("product_id"),rs.getInt("category_id"),
            rs.getString("product_name"),rs.getString("description"),rs.getBigDecimal("price"),
            rs.getString("image"),rs.getInt("stock"));
    }
    public List<Product> findAll(String keyword) throws Exception {
        List<Product> list=new ArrayList<>();
        String sql="SELECT * FROM Products WHERE status=1 AND product_name LIKE ? ORDER BY product_id DESC";
        try(Connection con=DB.getConnection(); PreparedStatement ps=con.prepareStatement(sql)) {
            ps.setString(1,"%"+(keyword==null?"":keyword.trim())+"%");
            try(ResultSet rs=ps.executeQuery()){while(rs.next())list.add(map(rs));}
        }
        return list;
    }
    public Product findById(int id) throws Exception {
        try(Connection con=DB.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT * FROM Products WHERE product_id=? AND status=1")) {
            ps.setInt(1,id);
            try(ResultSet rs=ps.executeQuery()){if(rs.next())return map(rs);}
        }
        return null;
    }
    public List<Product> adminAll() throws Exception {
        List<Product> list=new ArrayList<>();
        try(Connection con=DB.getConnection(); PreparedStatement ps=con.prepareStatement("SELECT * FROM Products ORDER BY product_id DESC");
            ResultSet rs=ps.executeQuery()){while(rs.next())list.add(map(rs));}
        return list;
    }
    public void add(int categoryId,String name,String desc,java.math.BigDecimal price,int stock) throws Exception {
        String sql="INSERT INTO Products(category_id,product_name,description,price,image,stock) VALUES(?,?,?,?, 'coffee-placeholder.jpg',?)";
        try(Connection con=DB.getConnection();PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1,categoryId);ps.setString(2,name);ps.setString(3,desc);ps.setBigDecimal(4,price);ps.setInt(5,stock);ps.executeUpdate();
        }
    }
    public void delete(int id) throws Exception {
        try(Connection con=DB.getConnection();PreparedStatement ps=con.prepareStatement("UPDATE Products SET status=0 WHERE product_id=?")){
            ps.setInt(1,id);ps.executeUpdate();
        }
    }
}
