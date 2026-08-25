package Store;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        List<String> toRemove = new ArrayList<>();

        for(CartItem ci : cart.getCartItems()) {
            String input = request.getParameter(ci.getProduct().getID());
            if (input != null) {
                try {
                    int newNum = Integer.parseInt(input);
                    if (newNum <= 0) {
                        toRemove.add(ci.getProduct().getID());
                    } else {
                        cart.updateProduct(ci.getProduct().getID(), newNum);
                    }
                } catch (NumberFormatException ignored) {}
            }
        }

        for (String s : toRemove) cart.updateProduct(s, 0);

        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
