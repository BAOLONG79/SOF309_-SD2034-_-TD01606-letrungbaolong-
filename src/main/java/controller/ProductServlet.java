package com.coffeepoly.controller;
import com.coffeepoly.dao.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
@WebServlet("/products")
public class ProductServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        try{
            req.setAttribute("products",new ProductDAO().findAll(req.getParameter("q")));
            req.setAttribute("keyword",req.getParameter("q"));
            req.getRequestDispatcher("/menu.jsp").forward(req,resp);
        }catch(Exception e){throw new ServletException(e);}
    }
}
