package com.cx.test;

import com.cx.client.client02.MobileCodeWSSoap;
import com.cx.client.client03.ArrayOfString;
import com.cx.client.client03.WeatherWSSoap;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 客户端调用webService的几种方式
 */
public class TestClient3 {
    public static void main(String[] args) throws MalformedURLException {
            test01();//调用手机号信息查询接口
            test02();//调用天气查询接口
    }
    /**
     * Java自带的方式 jax_ws
     * 调用手机号信息查询接口
     */
    private static void test01() throws MalformedURLException {
        QName qName = new QName("http://WebXml.com.cn/","MobileCodeWS");
        Service service = Service.create(new URL("http://ws.webxml.com.cn/WebServices/MobileCodeWS.asmx?wsdl"),qName);
        MobileCodeWSSoap port = service.getPort(MobileCodeWSSoap.class);
        String mobileCodeInfo = port.getMobileCodeInfo("13207234829", null);
        System.out.println(mobileCodeInfo);
    }
    /**
     * Java自带的方式  jax_ws
     * 调用天气查询接口
     * 注意：此wsdl文档需要更改下面这个，否则无法根据wsdl文档生成客户端代码
     *    将   <s:element ref="s:schema" />
     *         <s:any />
     *  改为：
     *          <s:any minOccurs="2" maxOccurs="2" />
     */
    private static void test02() throws MalformedURLException {
        //第一个参数：targetNamespace  第二个参数：wsdl:service
        QName qName = new QName("http://WebXml.com.cn/","WeatherWS");
        Service service = Service.create(new URL("http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl"),qName);
        //参数  对应的服务的 wsdl:portType
        WeatherWSSoap port = service.getPort(WeatherWSSoap.class);
        ArrayOfString weather = port.getWeather("西安", null);
        List<String> weatherString = weather.getString();
        for (String s : weatherString) {
            System.out.println(s);
        }
    }

}
