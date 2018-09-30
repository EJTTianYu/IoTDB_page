package main.java;

import main.java.IoTDB.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Overview2")
public class Overview2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip2=(String)request.getSession().getAttribute("ip");
        String port2=(String)request.getSession().getAttribute("porting");
        if(ip2.equals("192.168.130.7")&&port2.equals("3306")) {
            int num = 100;
            request.setAttribute("info", num);
            Storage[] s = new Storage[num];
            for (int i = 0; i < num; i++) {
                Storage e = new Storage("IoTDB" + i, i + 10000);
                s[i] = e;
                request.setAttribute("groupName" + i, s[i].getGroupName());
                request.setAttribute("groupSe" + i, s[i].getSerNum());

            }
            request.getRequestDispatcher("index4.jsp").forward(request, response);

        }

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
