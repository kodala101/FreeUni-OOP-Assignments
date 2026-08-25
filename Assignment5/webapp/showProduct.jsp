<%@ page import="Store.ProductList" %>
<%@ page import="Store.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <%
        String productId = request.getParameter("id");
        ProductList items = (ProductList) application.getAttribute("items");
        Product product = items.getItem(productId);
    %>

    <head>
        <title><%=product.getName()%></title>
    </head>

    <body>
        <h1><%=product.getName()%></h1>
        <img src="store-images/<%= product.getImg() %>" alt="<%= product.getName() %>"/>
        <p>Price: $<%=String.format("%.2f", product.getPrice())%></p>

        <form action="CartServlet" method="post">
            <input type="hidden" name="id" value="<%= product.getID() %>" />
            <input type="submit" value="Add to Cart" />
        </form>
    </body>
</html>

