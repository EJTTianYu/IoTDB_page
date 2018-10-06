import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.*;

@WebServlet(name = "showTime")
public class showTime extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String getIp=(String)request.getSession().getAttribute("ip");
        String getPort=(String)request.getSession().getAttribute("porting");
        String timeSe=request.getParameter("action");

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            Class.forName("cn.edu.tsinghua.iotdb.jdbc.TsfileDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JSONArray array=new JSONArray();

        try{
            connection= DriverManager.getConnection("jdbc:tsfile://"+getIp+":"+getPort+"/","root","root");
            statement=connection.createStatement();
            boolean hasResuleSet=statement.execute("show Timeseries "+timeSe);
            while (hasResuleSet){
                resultSet=statement.getResultSet();
                ResultSetMetaData metaData=resultSet.getMetaData();
                int column=metaData.getColumnCount();
                System.out.println(column);
                while(resultSet.next()){
                    JSONObject jsonObject=new JSONObject();
                    for(int j=1;j<=column;j++){
                        String columnName=metaData.getColumnLabel(j);
                        String value=resultSet.getString(columnName);
                        jsonObject.put(columnName,value);
                    }
                    array.put(jsonObject);
                }
                break;
            }
            System.out.println(array.toString());
            String file="/Users/tianyu/untitled/web/data.json";
            String charSet="UTF-8";
            //写字符转换成字节流
            FileOutputStream fileWriter=new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(fileWriter, charSet);
            try {
                writer.write(array.toString());
            } catch (Exception e) {
                // TODO: handle exception
            }finally{
                writer.close();
            }

        }
        catch (Exception e){

        }
        request.setAttribute("json",array.toString());
        request.getRequestDispatcher("timeSeShow.jsp").forward(request, response);

    }
}
