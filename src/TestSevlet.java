import IoTDB.Storage;

import java.io.IOException;
import java.io.PrintWriter;

public class TestSevlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
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
        request.getRequestDispatcher("index4.jsp").forward(request, response);
    }
}
