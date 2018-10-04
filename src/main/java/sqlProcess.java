import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "main.java.sqlProcess")
public class sqlProcess extends HttpServlet {
    String sqlTxt;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Ip = (String) request.getSession().getAttribute("ip");
        String Port = (String) request.getSession().getAttribute("porting");
        if (Ip == null && Port == null)
            request.getRequestDispatcher("index.jsp").forward(request, response);
        else{
            sqlTxt = request.getParameter("sqlText");
            request.getRequestDispatcher("dataComplete.jsp").forward(request, response);

        }

    }
}
