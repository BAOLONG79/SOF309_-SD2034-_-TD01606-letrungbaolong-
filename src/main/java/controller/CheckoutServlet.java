package com.coffeepoly.controller;

import com.coffeepoly.model.*;
import com.coffeepoly.util.DB;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        User u=(User)req.getSession().getAttribute("user");
        if(u==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)req.getSession().getAttribute("cart");
        if(cart==null||cart.isEmpty()){resp.sendRedirect(req.getContextPath()+"/cart");return;}
        req.getRequestDispatcher("/checkout.jsp").forward(req,resp);
    }
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        User u=(User)req.getSession().getAttribute("user");
        if(u==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)req.getSession().getAttribute("cart");
        if(cart==null||cart.isEmpty()){resp.sendRedirect(req.getContextPath()+"/cart");return;}
        String name=req.getParameter("receiverName"),phone=req.getParameter("phone"),address=req.getParameter("address");
        if(name==null||phone==null||address==null||name.isBlank()||phone.isBlank()||address.isBlank()){
            req.setAttribute("error","Vui lòng nhập đầy đủ thông tin nhận hàng.");doGet(req,resp);return;
        }
        BigDecimal total=BigDecimal.ZERO;
        for(CartItem item:cart.values())total=total.add(item.getSubtotal());
        String orderSql="INSERT INTO Orders(user_id,receiver_name,receiver_phone,delivery_address,total_amount,payment_method) VALUES(?,?,?,?,?,'COD')";
        try(Connection con=DB.getConnection()){
            con.setAutoCommit(false);
            try(PreparedStatement order=con.prepareStatement(orderSql,Statement.RETURN_GENERATED_KEYS)){
                order.setInt(1,u.getUserId());order.setString(2,name);order.setString(3,phone);
                order.setString(4,address);order.setBigDecimal(5,total);order.executeUpdate();
                int orderId;
                try(ResultSet keys=order.getGeneratedKeys()){if(!keys.next())throw new SQLException("Khong tao duoc ma don");orderId=keys.getInt(1);}
                try(PreparedStatement detail=con.prepareStatement("INSERT INTO OrderDetails(order_id,product_id,quantity,price) VALUES(?,?,?,?)")){
                    for(CartItem item:cart.values()){
                        detail.setInt(1,orderId);detail.setInt(2,item.getProduct().getProductId());
                        detail.setInt(3,item.getQuantity());detail.setBigDecimal(4,item.getProduct().getPrice());detail.addBatch();
                    }
                    detail.executeBatch();
                }
                con.commit();cart.clear();req.getSession().setAttribute("lastOrderId",orderId);
                resp.sendRedirect(req.getContextPath()+"/order-success.jsp");
            }catch(Exception e){con.rollback();throw e;}
        }catch(Exception e){throw new ServletException("Không thể tạo đơn hàng.",e);}
    }
}
