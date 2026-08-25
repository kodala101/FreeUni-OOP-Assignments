package Account;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = getServletContext();
        AccountManager manager = (AccountManager) context.getAttribute("manager");

        String name = request.getParameter("username");
        String pass = request.getParameter("password");

        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        if (manager.isPassword(name, pass)) {
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head><title>Welcome " + name + "</title></head>");
            pw.println("<body>");
            pw.println("<h1>Welcome " + name + "</h1>");
            pw.println("</body>");
            pw.println("</html>");
        } else {
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head><title>Information Incorrect</title></head>");
            pw.println("<body>");
            pw.println("<h1>Please Try Again</h1>");
            pw.println("<p>Either your user name or password is incorrect. Please try again.</p>");

            pw.println("<form action='LoginServlet' method='post'>");
            pw.println("User Name: <input type='text' name='username'><br><br>");
            pw.println("Password: <input type='password' name='password'>");
            pw.println("<input type='submit' value='Login'>");
            pw.println("</form>");

            pw.println("<br><a href='CreateAccountServlet'>Create New Account</a>");
            pw.println("</body>");
            pw.println("</html>");
        }
    }
}
