<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Đăng nhập - CoffeePoly</title>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    </head>

    <body>
        <nav class="nav">
            <a class="brand" href="<%=request.getContextPath()%>/">CoffeePoly</a>
            <a href="<%=request.getContextPath()%>/products">Menu</a>
        </nav>
        <main class="container">
            <form class="panel form" method="post" action="<%=request.getContextPath()%>/login">
                <h2>Đăng nhập</h2>
                <% if(request.getAttribute("error") !=null) { %>
                    <div class="error">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>
                        <% if("1".equals(request.getParameter("registered"))) { %>
                            <p style="color:green">Đăng ký thành công. Mời bạn đăng nhập.</p>
                            <% } %>

                                <div style="margin-bottom: 15px;">
                                    <label style="display: block; margin-bottom: 5px;">Email</label>
                                    <input type="email" name="email" required placeholder="name@example.com"
                                        style="width: 100%;">
                                </div>

                                <div style="margin-bottom: 15px;">
                                    <label style="display: block; margin-bottom: 5px;">Mật khẩu</label>
                                    <input type="password" name="password" required style="width: 100%;">
                                </div>

                                <button class="btn" style="width:100%; margin-top: 10px;">Đăng nhập</button>
                                <p class="muted" style="margin-top: 15px; text-align: center;">Chưa có tài khoản? <a
                                        href="<%=request.getContextPath()%>/register">Đăng ký</a></p>
            </form>
        </main>
    </body>

    </html>