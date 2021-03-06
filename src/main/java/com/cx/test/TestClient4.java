package com.cx.test;


import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 注意不同的包：
 *      <dependency>
 *       <groupId>commons-httpclient</groupId>
 *       <artifactId>commons-httpclient</artifactId>
 *       <version>3.1</version>
 *     </dependency>
 *  commons-httpclient   并入了 org.apache.httpcomponents » httpclient
 * httpClient方式调用webService服务(手机号信息查询)
 * 调用手机号信息查询接口
 */
public class TestClient4 {
    public static void main(String[] args) throws Exception {
//            test01();//用httpClient以soap协议的形式调用webService服务(手机号信息查询)
//            test02();//用httpClient以http  get的形式调用webService服务(手机号信息查询)
//            test03();//用httpClient以http  post的形式调用webService服务(手机号信息查询)
            test04();//用urlConnection的形式调用webService服务(手机号信息查询)

        }
    /**
     *
     *  以urlConnection形式调用webService服务(手机号信息查询)
     * 调用手机号信息查询接口
     */
    private static void test04() throws Exception {
        URL url = new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type","text/xml; charset=utf-8");
        urlConnection.setRequestProperty("SOAPAction","http://WebXml.com.cn/getMobileCodeInfo");
        //打开通信
        urlConnection.setDoInput(true);
        urlConnection.setDoOutput(true);
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <mobileCode>13207653789</mobileCode>\n" +
                "      <userID></userID>\n" +
                "    </getMobileCodeInfo>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        OutputStream os = urlConnection.getOutputStream();
        os.write(xml.getBytes());
        InputStream is = urlConnection.getInputStream();
        //方式一
//        String resultString ;
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
//        StringBuilder stringBuilder = new StringBuilder();
//        while ((resultString = reader.readLine()) != null) {
//            stringBuilder.append(resultString);
//        }
//        if (null != reader) {
//            reader.close();
//        }
        //方式二
        byte[] b = new byte[1024];
        int len = 0;
        String s = "";
        while( (len = is.read(b))!=-1 ){
            String ss = new String(b,0,len,"UTF-8");
            s += ss;
        }
        is.close();
        os.close();
        urlConnection.disconnect();
//        System.out.println(stringBuilder.toString());
        System.out.println(s);
    }


    /**
     *
     *  还是soap协议的形式！！！
     * httpClient方式调用webService服务(手机号信息查询)
     * 调用手机号信息查询接口
     */
    private static void test01() throws IOException {
        String url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx";
        String SOAPAction = "http://WebXml.com.cn/getMobileCodeInfo";
        HttpClient httpclient=new HttpClient();
        //设置超时时间
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(30000);
        PostMethod postMethod  = new PostMethod(url);
        //组装报文
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <mobileCode>13201536729</mobileCode>\n" +
                "      <userID></userID>\n" +
                "    </getMobileCodeInfo>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";

        byte[] b = xml.getBytes("UTF-8");
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(is, b.length, "text/xml; charset=UTF-8");
        postMethod.setRequestEntity(requestEntity);
//        postMethod.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
        postMethod.setRequestHeader("soapActionString", SOAPAction);
        // 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 执行getMethod
        int statusCode = httpclient.executeMethod(postMethod);
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("同步失败: " + postMethod.getStatusLine());
        }
        String resultString = postMethod.getResponseBodyAsString();
        System.out.println(resultString);

    }


    /**
     *
     *  以http  get 形式！！！
     * httpClient方式调用webService服务(手机号信息查询)
     * 调用手机号信息查询接口
     */
    private static void test02() throws IOException {
        String url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo?mobileCode=13201546538&userID=";
        HttpClient httpclient=new HttpClient();
        //设置超时时间
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(30000);
        GetMethod getMethod  = new GetMethod(url);
        // 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 执行getMethod
        int statusCode = httpclient.executeMethod(getMethod);
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("同步失败: " + getMethod.getStatusLine());
        }
        String resultString = getMethod.getResponseBodyAsString();
        System.out.println(resultString);

    }


    /**
     *
     *  以http  post的形式！！！
     * httpClient方式调用webService服务(手机号信息查询)
     * 调用手机号信息查询接口
     */
    private static void test03() throws IOException {
        String url = "http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx/getMobileCodeInfo";
        HttpClient httpclient=new HttpClient();
        //设置超时时间
        httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(30000);
        httpclient.getHttpConnectionManager().getParams().setSoTimeout(30000);
        PostMethod postMethod  = new PostMethod(url);
        //组装报文
        String xml = "mobileCode=13201547368&userID=";

        byte[] b = xml.getBytes("UTF-8");
        InputStream is = new ByteArrayInputStream(b, 0, b.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(is, b.length, "application/x-www-form-urlencoded; charset=UTF-8");
        postMethod.setRequestEntity(requestEntity);
//        postMethod.setRequestHeader("Content-Type", "text/xml; charset=utf-8");
        // 设置成了默认的恢复策略，在发生异常时候将自动重试3次，在这里你也可以设置成自定义的恢复策略
        postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        // 执行getMethod
        int statusCode = httpclient.executeMethod(postMethod);
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("同步失败: " + postMethod.getStatusLine());
        }
        String resultString = postMethod.getResponseBodyAsString();
        System.out.println(resultString);

    }
}
