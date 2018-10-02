package main.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Struct;
import java.util.ArrayList;

import main.java.IoTDB.Storage;

@WebServlet(name = "Overview")
public class Overview extends HttpServlet {
    private String Ip = "";
    private String Port = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ip = request.getParameter("IP");
        Port = request.getParameter("port");
        if (Ip.equals("192.168.130.7") && Port.equals("3306")) {
            int num = 100;
            request.setAttribute("info", num);
            Storage[] s = new Storage[num];
            for (int i = 0; i < num; i++) {
                Storage e = new Storage("IoTDB" + i, i + 10000);
                s[i] = e;
                request.setAttribute("groupName" + i, s[i].getGroupName());
                request.setAttribute("groupSe" + i, s[i].getSerNum());

            }
            request.getRequestDispatcher("indexData.jsp").forward(request, response);
            request.getSession().setAttribute("ip", Ip);
            request.getSession().setAttribute("porting", Port);
        } else {

            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ip = (String) request.getSession().getAttribute("ip");
        Port = (String) request.getSession().getAttribute("porting");
        if (Ip == null && Port == null)
            request.getRequestDispatcher("index.jsp").forward(request, response);
        else {
            if (Ip.equals("192.168.130.7") && Port.equals("3306")) {
                int num = 100;
                request.setAttribute("info", num);
                Storage[] s = new Storage[num];
                for (int i = 0; i < num; i++) {
                    Storage e = new Storage("IoTDB" + i, i + 10000);
                    s[i] = e;
                    request.setAttribute("groupName" + i, s[i].getGroupName());
                    request.setAttribute("groupSe" + i, s[i].getSerNum());

                }
                request.getRequestDispatcher("indexData.jsp").forward(request, response);
                request.getSession().setAttribute("ip", Ip);
                request.getSession().setAttribute("porting", Port);
            } else {

                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }
}
