package com.coffeepoly.controller;

import com.coffeepoly.dao.UserDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.getRequestDispatcher("/register.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        String name=req.getParameter("fullName"), email=req.getParameter("email"),
            phone=req.getParameter("phone"), pass=req.getParameter("password"),
            confirm=req.getParameter("confirmPassword");
        if(name==null||email==null||pass==null||!pass.equals(confirm)||pass.length()<6){
            req.setAttribute("error","Vui lòng nhập đầy đủ thông tin; mật khẩu tối thiểu 6 ký tự và xác nhận phải khớp.");
            doGet(req,resp); return;
        }
        try{
            new UserDAO().register(name.trim(),email.trim().toLowerCase(),phone,pass);
            resp.sendRedirect(req.getContextPath()+"/login?registered=1");
        }catch(Exception e){
            req.setAttribute("error","Đăng ký không thành công. Email có thể đã được sử dụng.");
            doGet(req,resp);
        }
    }
}
