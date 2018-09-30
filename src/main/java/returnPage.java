package main.java;

import main.java.IoTDB.Storage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "returnPage")
public class returnPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String a=request.getParameter("action");
        int b=Integer.parseInt(a);

        int num=100;
        request.setAttribute("info", num);
        Storage[] s=new Storage[num];
        for(int i=0;i<num;i++){
            Storage e=new Storage("IoTDB"+i,i+10000);
            s[i]=e;

        }
        for(int i=0;i<7;i++) {
            if(7*b+i-7<num){
                request.setAttribute("groupName" + i, s[7*b+i-7].getGroupName());
                request.setAttribute("groupSe" + i, s[7*b+i-7].getSerNum());
            }
            else{
                request.setAttribute("groupName" + i, "");
                request.setAttribute("groupSe" + i,"" );
            }

        }
        request.getRequestDispatcher("indexData.jsp").forward(request, response);

    }
}
