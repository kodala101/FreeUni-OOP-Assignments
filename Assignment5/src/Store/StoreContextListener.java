package Store;

import Account.AccountManager;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class StoreContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductList items = new ProductList();
        ServletContext context = sce.getServletContext();
        context.setAttribute("items", items);
    }
}
