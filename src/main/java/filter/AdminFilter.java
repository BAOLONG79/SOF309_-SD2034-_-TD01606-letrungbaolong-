package com.coffeepoly.filter;
import com.coffeepoly.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
        HttpServletRequest req=(HttpServletRequest)request;HttpServletResponse resp=(HttpServletResponse)response;
        HttpSession session=req.getSession(false);User user=session==null?null:(User)session.getAttribute("user");
        if(user==null){resp.sendRedirect(req.getContextPath()+"/login");return;}
        if(!"ADMIN".equals(user.getRole())){resp.sendError(HttpServletResponse.SC_FORBIDDEN,"Bạn không có quyền truy cập.");return;}
        chain.doFilter(request,response);
    }
}
