package com.cx.test;




import com.cx.client.client01.WeatherServiceImpl;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

/**
 * 第二种方式调用：
 */
public class TestClient2 {
    public static void main(String[] args) throws Exception {

        QName qName  = new QName("http://impl.Service.web.cx.com/", "WeatherServiceImplService");

        Service service = Service.create(new URL("http://localhost:8888/ws?wsdl"), qName);

        WeatherServiceImpl portType  = service.getPort(WeatherServiceImpl.class);

        String result = portType .queryWeatherByCityName("北京");
        System.out.println(result);
    }
}
