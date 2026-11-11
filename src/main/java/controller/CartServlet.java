package com.coffeepoly.controller;

import com.coffeepoly.dao.ProductDAO;
import com.coffeepoly.model.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @SuppressWarnings("unchecked")
    private Map<Integer,CartItem> cart(HttpSession s){
        Map<Integer,CartItem> c=(Map<Integer,CartItem>)s.getAttribute("cart");
        if(c==null){c=new LinkedHashMap<>();s.setAttribute("cart",c);}return c;
    }
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        Map<Integer,CartItem> c=cart(req.getSession());
        req.setAttribute("cartItems",c.values());
        req.getRequestDispatcher("/cart.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        Map<Integer,CartItem> c=cart(req.getSession());
        String action=req.getParameter("action");
        try{
            int id=Integer.parseInt(req.getParameter("productId"));
            if("add".equals(action)){
                Product p=new ProductDAO().findById(id);
                if(p!=null){CartItem item=c.get(id);if(item==null)c.put(id,new CartItem(p,1));else item.setQuantity(item.getQuantity()+1);}
            }else if("remove".equals(action)){c.remove(id);}
            else if("update".equals(action)){
                int qty=Integer.parseInt(req.getParameter("quantity"));
                if(qty<=0)c.remove(id);else if(c.containsKey(id))c.get(id).setQuantity(Math.min(qty,99));
            }
        }catch(Exception ignored){}
        resp.sendRedirect(req.getContextPath()+"/cart");
    }
}
