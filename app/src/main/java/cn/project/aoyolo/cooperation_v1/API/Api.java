package cn.project.aoyolo.cooperation_v1.API;

import com.lidroid.xutils.HttpUtils;

import org.kymjs.kjframe.KJHttp;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Hy on 2015/11/2.
 */
public class Api
{
    public static KJHttp.Builder httpBuilder = new KJHttp.Builder();
    //后台地址
    private final static String RURL="http://andrewlu.cn/SupplyAndDemondPlatform";


    //测试地址



    //现在使用地址
    public final static String NURL=RURL;
        //繁琐，失败
//    public static String readContentFromPost(URL url,String content) throws IOException{
//        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//        //post请求要放在http正文处
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//        connection.setRequestMethod("POST");
//        //POST请求不能用环缓存
//        connection.setUseCaches(false);
//        connection.setInstanceFollowRedirects(true);
//        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
//        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
//        // 进行编码
//        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//        out.writeBytes(content);
//        out.flush();
//        out.close();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String line ="";
//        while ((line = reader.readLine()) != null){
//            System.out.println(line);
//        }
//        reader.close();
//        connection.disconnect();
//        return line;
//    }
//    public static String uploadFromPost(URL url,File file) throws  IOException{
//        HttpURLConnection connection =(HttpURLConnection) url.openConnection();
//        connection.setDoOutput(true);
//        connection.setDoInput(true);
//        connection.setRequestMethod("POST");
//        //POST请求不能用环缓存
//        connection.setUseCaches(false);
//        connection.setInstanceFollowRedirects(true);
//        connection.setRequestProperty("Connection", "Keep-Alive");
//        connection.setRequestProperty("Charset", "UTF-8");
//        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//        // 配置本次连接的Content-type，配置为multipart/form-data的
//        connection.setRequestProperty("Content-type", "multipart/form-data;boundary=*****");
//        //connection.connect();
//        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//        FileInputStream in = new FileInputStream(file);
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        byte[] b = new byte[1024];
//        int n;
//        while ((n = in.read(b)) != -1) {
//            output.write(b, 0, n);
//        }
//        in.close();
//        out.writeBytes("pics=");
//        out.writeBytes("Content-type: " +"application/octet-stream");
//        out.write(output.toByteArray());
//        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//        String line ="";
//        while ((line = reader.readLine()) != null){
//            System.out.println(line);
//        }
//        reader.close();
//        connection.disconnect();
//        return line;
//    }
}
