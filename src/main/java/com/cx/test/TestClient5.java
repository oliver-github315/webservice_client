package com.cx.test;


import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.dom4j.*;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.List;


/**
 *      <dependency>
 *       <groupId>org.apache.httpcomponents</groupId>
 *       <artifactId>httpclient</artifactId>
 *       <version>4.5.3</version>
 *     </dependency>
 *     这个jar包下的httpClient调用webService的方法
 */
public class TestClient5 {
    public static void main(String[] args) throws IOException, DocumentException {
        String retStr ;
        String SOAPAction = "http://WebXml.com.cn/getMobileCodeInfo";
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        CloseableHttpClient httpClient = httpClientBuilder.build();
        HttpPost httpPost = new HttpPost("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx");
        //  设置请求和传输超时时间
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(30000)
                .setConnectTimeout(30000).build();
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type", "text/xml;charset=UTF-8");
        httpPost.setHeader("SOAPAction", SOAPAction);
        //请求内容
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getMobileCodeInfo xmlns=\"http://WebXml.com.cn/\">\n" +
                "      <mobileCode>13201536729</mobileCode>\n" +
                "      <userID></userID>\n" +
                "    </getMobileCodeInfo>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        StringEntity data = new StringEntity(xml, Charset.forName("UTF-8"));
        httpPost.setEntity(data);
        //发送请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity responseEntity = response.getEntity();
        System.out.println(responseEntity);
        if (responseEntity != null) {
            // 打印响应内容
            retStr = EntityUtils.toString(responseEntity, "UTF-8");
            System.err.println("response:" + retStr);
            //方式一：document
//            Document document = DocumentHelper.parseText(retStr);
//            Element rootElement = document.getRootElement();
//            List<Element> elementList = rootElement.elements();
//            for (Element element : elementList) {
//                System.out.println(element.getName()+"---"+element.getStringValue());
//            }
            //方式二：SaxReader
            SAXReader reader = new SAXReader();
            StringReader stringReader = new StringReader(retStr);
            Document document = reader.read(stringReader);
            Element rootElement = document.getRootElement();
            List<Element> elementList = rootElement.elements();
            for (Element element : elementList) {
                System.out.println(element.getName()+"---"+element.getStringValue());
            }
            httpClient.close();
        }
    }
}
