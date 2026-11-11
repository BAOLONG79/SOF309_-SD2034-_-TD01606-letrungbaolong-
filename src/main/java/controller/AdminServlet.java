package com.coffeepoly.controller;
import com.coffeepoly.dao.ProductDAO;
import com.coffeepoly.util.DB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        try{
            req.setAttribute("products",new ProductDAO().adminAll());
            try(Connection con=DB.getConnection();PreparedStatement ps=con.prepareStatement(
                "SELECT o.order_id,u.full_name,o.receiver_name,o.receiver_phone,o.delivery_address,o.total_amount,o.order_status,o.created_at FROM Orders o JOIN Users u ON o.user_id=u.user_id ORDER BY o.order_id DESC");
                ResultSet rs=ps.executeQuery()){
                java.util.List<java.util.Map<String,Object>> orders=new java.util.ArrayList<>();
                while(rs.next()){
                    java.util.Map<String,Object> row=new java.util.LinkedHashMap<>();
                    row.put("id",rs.getInt("order_id"));row.put("customer",rs.getString("full_name"));
                    row.put("receiver",rs.getString("receiver_name"));row.put("phone",rs.getString("receiver_phone"));
                    row.put("address",rs.getString("delivery_address"));row.put("total",rs.getBigDecimal("total_amount"));
                    row.put("status",rs.getString("order_status"));row.put("date",rs.getTimestamp("created_at"));
                    orders.add(row);
                }
                req.setAttribute("orders",orders);
            }
            req.getRequestDispatcher("/admin/dashboard.jsp").forward(req,resp);
        }catch(Exception e){throw new ServletException(e);}
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        try{
            String action=req.getParameter("action");
            if("add".equals(action)){
                new ProductDAO().add(Integer.parseInt(req.getParameter("categoryId")),
                    req.getParameter("name"),req.getParameter("description"),
                    new BigDecimal(req.getParameter("price")),Integer.parseInt(req.getParameter("stock")));
            }else if("delete".equals(action)){
                new ProductDAO().delete(Integer.parseInt(req.getParameter("productId")));
            }else if("status".equals(action)){
                try(Connection con=DB.getConnection();PreparedStatement ps=con.prepareStatement("UPDATE Orders SET order_status=? WHERE order_id=?")){
                    ps.setString(1,req.getParameter("status"));ps.setInt(2,Integer.parseInt(req.getParameter("orderId")));ps.executeUpdate();
                }
            }
            resp.sendRedirect(req.getContextPath()+"/admin");
        }catch(Exception e){throw new ServletException("Thao tác quản trị thất bại.",e);}
    }
}
