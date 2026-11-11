package com.coffeepoly.controller;

import com.coffeepoly.dao.UserDAO;
import com.coffeepoly.model.User;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.getRequestDispatcher("/login.jsp").forward(req,resp);
    }
    protected void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException{
        req.setCharacterEncoding("UTF-8");
        try{
            User user=new UserDAO().login(req.getParameter("email"),req.getParameter("password"));
            if(user==null){req.setAttribute("error","Email hoặc mật khẩu không chính xác.");doGet(req,resp);return;}
            HttpSession old=req.getSession(false); if(old!=null)old.invalidate();
            HttpSession session=req.getSession(true);session.setAttribute("user",user);
            String target="ADMIN".equals(user.getRole())?"/admin":"/products";
            resp.sendRedirect(req.getContextPath()+target);
        }catch(Exception e){throw new ServletException("Lỗi đăng nhập. Kiểm tra kết nối database.",e);}
    }
}
