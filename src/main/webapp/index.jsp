<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.coffeepoly.model.User" %>
<%
User current=(User)session.getAttribute("user");
%>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>CoffeePoly - Cà phê mỗi ngày</title><link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"></head>
<body>
<nav class="nav"><a class="brand" href="<%=request.getContextPath()%>/">CoffeePoly</a><div class="navlinks">
<a href="<%=request.getContextPath()%>/">Trang chủ</a><a href="<%=request.getContextPath()%>/products">Menu</a><a href="<%=request.getContextPath()%>/cart">Giỏ hàng</a>
<%if(current==null){%><a href="<%=request.getContextPath()%>/login">Đăng nhập</a><a class="btn" href="<%=request.getContextPath()%>/register">Đăng ký</a><%}else{%><span>Xin chào, <%=current.getFullName()%></span><%if("ADMIN".equals(current.getRole())){%><a href="<%=request.getContextPath()%>/admin">Quản trị</a><%}%><a href="<%=request.getContextPath()%>/logout">Đăng xuất</a><%}%>
</div></nav>
<main class="container"><section class="hero"><p>COFFEE • FRESH • EVERY DAY</p><h1>Một tách cà phê, một ngày đầy cảm hứng.</h1><p>Khám phá hương vị cà phê yêu thích tại CoffeePoly. Chọn món, đặt hàng và tận hưởng từng khoảnh khắc.</p><a class="btn light" href="<%=request.getContextPath()%>/products">Khám phá menu →</a></section>
<section style="padding:35px 0"><h2>Chào mừng đến CoffeePoly</h2><p class="muted">Cà phê ngon bắt đầu từ những điều giản dị.</p><div class="grid">
<div class="panel"><h3>☕ Cà phê chất lượng</h3><p class="muted">Menu đa dạng từ cà phê Việt Nam đến espresso.</p></div>
<div class="panel"><h3>🛒 Đặt hàng tiện lợi</h3><p class="muted">Thêm món vào giỏ và đặt hàng trực tuyến.</p></div>
<div class="panel"><h3>🚚 Nhận hàng dễ dàng</h3><p class="muted">Thanh toán khi nhận hàng (COD).</p></div>
</div></section></main><footer class="footer">© CoffeePoly • Fresh coffee, happy day.</footer></body></html>
