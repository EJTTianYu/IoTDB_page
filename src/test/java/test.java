
import java.io.File;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.*;


public class test {
    public static void main(String[] args) throws IOException {
        String file="stream.txt";
        String charSet="UTF-8";
        //写字符转换成字节流
        FileOutputStream fileWriter=new FileOutputStream(file);
        OutputStreamWriter writer=new OutputStreamWriter(fileWriter, charSet);
        try {
            writer.write("测试输入字符串1");
        } catch (Exception e) {
            // TODO: handle exception
        }finally{
            writer.close();
        }
        try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

            //PrintWriter writer = new PrintWriter("data.txt", "UTF-8");
            //writer.write("去屎吧");
            //writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


