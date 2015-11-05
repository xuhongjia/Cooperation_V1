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

/**
 * Created by Hy on 2015/11/2.
 */
public class Api
{
    public static KJHttp.Builder httpBuilder = new KJHttp.Builder();
    //后台地址
    private final static String RURL="http://andrewlu.cn/SupplyAndDemondPlatform";
    private final static String HYDURL="http://192.168.1.114:8080/web";//HY宿舍测试地址
    private final static String HYGURL="http://192.168.16.2:8080/web";//HY公司地址
    //测试地址



    //现在使用地址
    public final static String NURL=RURL;

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
//        connection.connect();
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
//        connection.setDoInput(true);
//        connection.setDoInput(true);
//        //POST请求不能用环缓存
//        connection.setUseCaches(false);
//        connection.setInstanceFollowRedirects(true);
//        // 配置本次连接的Content-type，配置为multipart/form-data的
//        connection.setRequestProperty("Content-Type", "multipart/form-data");
//        connection.connect();
//        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//        FileInputStream in = new FileInputStream(file);
//        ByteArrayOutputStream output = new ByteArrayOutputStream();
//        byte[] b = new byte[1024];
//        int n;
//        while ((n = in.read(b)) != -1) {
//            output.write(b, 0, n);
//        }
//        in.close();
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
