package guide;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class HelloWorldServlet extends HttpServlet {
    private String mymsg = "Http Servlet Demo";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.printf("<h1>%s</h1>\r\n", mymsg);
        out.printf("<p>Ahoy ahoy!</p>\r\n");
    }
}
