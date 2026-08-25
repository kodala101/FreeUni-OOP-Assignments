<%@ page import="Store.ProductList" %>
<%@ page import="Store.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>Student Store</title>
    </head>

    <body>
        <h1>Student Store</h1>
        <p>Items available:</p>

        <ul>
            <%
                ProductList items = (ProductList)application.getAttribute("items");

                for (Product p : items.getAllItems()) {
            %>
                    <li>
                        <a href="showProduct.jsp?id=<%= p.getID() %>"> <%= p.getName() %> </a>
                    </li>
            <%  }  %>
        </ul>
    </body>
</html>