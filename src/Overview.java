import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Struct;
import java.util.ArrayList;

import IoTDB.Storage;

@WebServlet(name = "Overview")
public class Overview extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num=100;
        request.setAttribute("info", num);
        Storage[] s=new Storage[num];
        for(int i=0;i<num;i++){
            Storage e=new Storage("IoTDB"+i,i+10000);
            s[i]=e;
            request.setAttribute("groupName"+i,s[i].getGroupName());
            request.setAttribute("groupSe"+i,s[i].getSerNum());

        }

        request.getRequestDispatcher("index3.jsp").forward(request, response);

    }
}
