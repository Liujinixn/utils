package utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonReptile {

    /**
     * 爬去url中的json数据，保存至本地文件中
     * @param jsonUrl
     * @param filePath
     * @return
     */
    public static boolean startReptile(String jsonUrl,String filePath) {
        String restring = read(jsonUrl);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            fos.write(restring.getBytes());
            fos.close();
            System.out.println("File Storage Successful...");
        } catch (IOException e) {
            System.out.println("File Storage Error...");
            e.printStackTrace();
        }
        return true;
    }

    //创建读取接口中数据的方法
    private static String read(String dizhi) {
        URL url = null;
        BufferedReader reader = null;
        HttpURLConnection connection = null;
        InputStreamReader ins = null;

        try {
            // 设置url地址
            url = new URL(dizhi);
            System.out.println("已完成20%。。。");
            // 获取连接通道
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接响应时间
            connection.setConnectTimeout(2 * 1000);
            // 设置读取响应时间
            connection.setReadTimeout(2 * 1000);

            // 连接
            connection.connect();
            System.out.println("已完成50%。。。");
            // 输入流
            ins = new InputStreamReader(connection.getInputStream(), "UTF-8");
            // 读取
            reader = new BufferedReader(ins);
            // 创建可变字符串
            StringBuffer sb = new StringBuffer();
            System.out.println("已完成80%。。。");
            String line;

//			readLine()方法，当读取流读取数据时使用，当读到\n、\r时，会跟着换行，
//			同时会以字符串的形式返回这一行，当读取完所有数据时，会返回null
            while ((line = reader.readLine()) != null) {
                System.out.println("导入中。。。");
                sb.append(line + "\n");
            }
            return sb.toString();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error GetURL:" + e);
            System.out.println("Error GetURL:" + url);
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;

    }
}
