package com.cx.test;


import com.cx.client.client01.WeatherServiceImpl;
import com.cx.client.client01.WeatherServiceImplService;

/**
 * 第一种方式调用：基于服务视图名称对象的调用
 */
public class TestClient {
    public static void main(String[] args) {

        //客户端调用
        //1.创建服务视图对象
        WeatherServiceImplService weatherServiceImplService = new WeatherServiceImplService();
        //2.通过wsdl binding -- portType   直接获取portType
        WeatherServiceImpl weatherServiceImplPort = weatherServiceImplService.getWeatherServiceImplPort();
        //3.调用服务中的方法
        String result = weatherServiceImplPort.queryWeatherByCityName("北京");
        System.out.println(result);
        System.out.println("哈哈哈");
    }
}
