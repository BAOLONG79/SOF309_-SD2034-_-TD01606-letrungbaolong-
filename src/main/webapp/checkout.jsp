<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.coffeepoly.model.User" %>
<%
User u=(User)session.getAttribute("user");
%>
<!DOCTYPE html><html lang="vi"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width,initial-scale=1"><title>Thanh toán - CoffeePoly</title><link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"></head><body><nav class="nav"><a class="brand" href="<%=request.getContextPath()%>/">CoffeePoly</a><a href="<%=request.getContextPath()%>/cart">Quay lại giỏ hàng</a></nav>
<main class="container"><form class="panel form" method="post" action="<%=request.getContextPath()%>/checkout"><h2>Thông tin nhận hàng</h2><%if(request.getAttribute("error")!=null){%><div class="error"><%=request.getAttribute("error")%></div><%}%><label>Người nhận</label><input name="receiverName" value="<%=u==null?"":u.getFullName()%>" required><label>Số điện thoại</label><input name="phone" value="<%=u==null?"":(u.getPhone()==null?"":u.getPhone())%>" required><label>Địa chỉ giao hàng</label><textarea name="address" rows="3" required></textarea><label>Phương thức thanh toán</label><input value="Thanh toán khi nhận hàng (COD)" readonly><button class="btn" style="width:100%">Xác nhận đặt hàng</button></form></main></body></html>
