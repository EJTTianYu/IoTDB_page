
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//解决单机页面的翻页问题

@WebServlet(name = "returnPage")
public class returnPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a=request.getParameter("action");
        int nowPage=Integer.parseInt(a);


        request.setAttribute("cPage", nowPage);

        request.getRequestDispatcher("indexPage.jsp").forward(request, response);

    }
}
