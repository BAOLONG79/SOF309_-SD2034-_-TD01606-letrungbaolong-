package com.coffeepoly.controller;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws IOException{
        HttpSession s=req.getSession(false);if(s!=null)s.invalidate();
        resp.sendRedirect(req.getContextPath()+"/");
    }
}
