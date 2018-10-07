import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "main.java.sqlProcess")
public class sqlProcess extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sqlTxt;
        Connection connection;
        Statement statement;
        ResultSet resultSet;

        String Ip = (String) request.getSession().getAttribute("ip");
        String Port = (String) request.getSession().getAttribute("porting");

        if (Ip == null && Port == null)
            request.getRequestDispatcher("index.jsp").forward(request, response);
        else{
            try {
                Class.forName("cn.edu.tsinghua.iotdb.jdbc.TsfileDriver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try{
                JSONArray array=new JSONArray();
                List<String> columns=new ArrayList<String>() ;

                sqlTxt=request.getParameter("sqlText");
                //System.out.println(sqlTxt);
                connection= DriverManager.getConnection("jdbc:tsfile://"+Ip+":"+Port+"/","root","root");
                statement=connection.createStatement();
                System.out.println(0);
                boolean hasResultSet=statement.execute(sqlTxt);

                System.out.println(1);
                if(hasResultSet) {
                    resultSet = statement.getResultSet();
                    ResultSetMetaData metaData=resultSet.getMetaData();
                    //System.out.println(metaData);
                    int column=metaData.getColumnCount();
                    //System.out.println(column);

                    for(int j=1;j<=column;j++){
                        String columnName=metaData.getColumnLabel(j);
                        columns.add(columnName);
                    }
                    //System.out.println(column);

                    while (resultSet.next()) {
                        JSONObject jsonObject=new JSONObject();
                        for(int j=1;j<=column;j++){
                            String columnName=metaData.getColumnLabel(j);
                            //System.out.println(column);
                            String value=resultSet.getString(columnName);
                            //System.out.println(column);
                            jsonObject.put(columnName,value);
                        }
                        array.put(jsonObject);
                    }
                    //System.out.println(array.toString());
                    //System.out.println(columns);
                    request.setAttribute("field",columns);
                    request.setAttribute("fieldNum",columns.size());
                    System.out.println(columns.get(0));
                    request.setAttribute("return",array.toString());
                    request.getRequestDispatcher("dataComplete.jsp").forward(request, response);
                }
                else{
                    request.getRequestDispatcher("dataOperation.jsp").forward(request, response);

                }


            }
            catch (Exception e){
                request.getRequestDispatcher("dataOperation.jsp").forward(request, response);

            }
        }

    }
}
