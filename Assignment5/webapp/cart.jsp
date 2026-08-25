<%@ page import="Store.Cart" %>
<%@ page import="Store.CartItem" %>
<%@ page import="Store.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Cart cart = (Cart) session.getAttribute("cart");
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Cart</title>
    </head>
    <body>
        <h1>Shopping Cart</h1>

        <form action="UpdateServlet" method="post">
            <ul>
                <% for(CartItem ci : cart.getCartItems()) { %>
                    <li>
                        <input type="text" name="<%=ci.getProduct().getID()%>" value="<%=ci.getNum()%>">
                        <a><%=ci.getProduct().getName() + ", " + String.format("%.2f", ci.getCost())%></a><br>
                    </li>
                <%}%>
            </ul>
            <a>Total: $<%=String.format("%.2f", cart.totalCost())%></a>
            <input type="submit" name="update" value="Update Cart"><br><br>
        </form>
            <a href="store.jsp">Continue Shopping</a>
    </body>
</html>