package main.java;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "sqlProcess")
public class sqlProcess extends HttpServlet {
    String sqlTxt;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        sqlTxt = request.getParameter("sqlText");
        PrintWriter out = response.getWriter(); //声明out对象，用来向客户端返回数据
        out.print(sqlTxt);    //get请求返回html标签
    }
}
