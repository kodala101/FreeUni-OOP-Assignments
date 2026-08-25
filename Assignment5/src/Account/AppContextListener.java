package Account;

import Store.ProductList;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        AccountManager manager = new AccountManager();
        ServletContext context = sce.getServletContext();
        context.setAttribute("manager", manager);
    }
}
