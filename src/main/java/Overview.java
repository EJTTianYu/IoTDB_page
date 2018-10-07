import cn.edu.tsinghua.iotdb.jdbc.TsfileJDBCConfig;
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
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;



@WebServlet(name = "main.java.Overview")
public class Overview extends HttpServlet {
    private String Ip = "";
    private String Port = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ip = request.getParameter("IP");
        Port = request.getParameter("port");

        Connection connection;
        Statement statement;
        ResultSet resultSet;
        Statement statement1;

        int storagenum=0;  //用于存储存储组的总数量



        try {
            Class.forName("cn.edu.tsinghua.iotdb.jdbc.TsfileDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        try{

            connection= DriverManager.getConnection("jdbc:tsfile://"+Ip+":"+Port+"/","root","root");
            statement=connection.createStatement();
            statement1=connection.createStatement();
            //DatabaseMetaData databaseMetaData=connection.getMetaData();
            //String metadata=databaseMetaData.toString();

            //resultSet=databaseMetaData.getColumns(null,null,"root.*",null);
            //Set<String> storageGroups = new HashSet<String>();

            boolean hasResultSet=statement.execute("show storage group");
            //boolean hasResultSet1=statement1.execute("show timeseries root");
            JSONArray array=new JSONArray();
            while(hasResultSet){
                resultSet = statement.getResultSet();
                int i=0;
                while (resultSet.next()) {
                    request.getSession().setAttribute("groupName"+i,resultSet.getString(1));
                    int timeSeNum=0;
                    boolean hasResuleSet1=statement1.execute("show Timeseries "+resultSet.getString(1));
                    while (hasResuleSet1){
                        ResultSet resultSet1=statement1.getResultSet();
                        ResultSetMetaData metaData=resultSet1.getMetaData();
                        int column=metaData.getColumnCount();
                        //System.out.println(column);
                        while(resultSet1.next()){
                            timeSeNum++;
                            JSONObject jsonObject=new JSONObject();
                            for(int j=1;j<=column;j++){
                                String columnName=metaData.getColumnLabel(j);
                                String value=resultSet1.getString(columnName);
                                jsonObject.put(columnName,value);
                            }
                            array.put(jsonObject);
                        }
                        break;
                    }
                    request.getSession().setAttribute("groupNo"+i,timeSeNum);;
                    storagenum++;
                    i++;
                }
                break;
            }

            //System.out.println(array.toString());
            //PrintWriter writer = new PrintWriter("data.txt", "UTF-8");
            //writer.write("woccccc");/Users/tianyu/untitled/
            //writer.close();
            //String file="/Users/tianyu/untitled/stream.txt";
            String file="/Users/tianyu/untitled/web/data1.json";
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

            request.getSession().setAttribute("groupNum",storagenum);

            /*String sql = "select * from root.vehicle";
            boolean hasResultSet = statement.execute(sql);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String path="root.*";
            if (hasResultSet) {
                ResultSet res = statement.getResultSet();
                System.out.println("                    Time" + "|" + path);
                while (res.next()) {
                    long time = Long.parseLong(res.getString("Time"));
                    String dateTime = dateFormat.format(new Date(time));
                    System.out.println(dateTime + " | " + res.getString(path));
                }
            }
            while(hasResultSet1){
                        ResultSet resultSet1=statement1.getResultSet();
                        while(resultSet1.next()){
                            request.setAttribute("groupNo",resultSet1.getString(1));
                        }
                        break;
                    }*/
            request.getRequestDispatcher("indexData.jsp").forward(request, response);
            request.getSession().setAttribute("ip", Ip);
            request.getSession().setAttribute("porting", Port);
        }
        catch (Exception e){
            e.printStackTrace();
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }






    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Ip = (String) request.getSession().getAttribute("ip");
        Port = (String) request.getSession().getAttribute("porting");
        if (Ip == null && Port == null)
            request.getRequestDispatcher("index.jsp").forward(request, response);
        else {

                request.getRequestDispatcher("indexData.jsp").forward(request, response);

        }
    }
}
