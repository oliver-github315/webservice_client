
package com.cx.client.client01;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.cx.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _QueryWeatherByCityNameResponse_QNAME = new QName("http://impl.Service.web.cx.com/", "queryWeatherByCityNameResponse");
    private final static QName _QueryWeatherByCityName_QNAME = new QName("http://impl.Service.web.cx.com/", "queryWeatherByCityName");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.cx.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryWeatherByCityNameResponse }
     * 
     */
    public QueryWeatherByCityNameResponse createQueryWeatherByCityNameResponse() {
        return new QueryWeatherByCityNameResponse();
    }

    /**
     * Create an instance of {@link QueryWeatherByCityName }
     * 
     */
    public QueryWeatherByCityName createQueryWeatherByCityName() {
        return new QueryWeatherByCityName();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWeatherByCityNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.Service.web.cx.com/", name = "queryWeatherByCityNameResponse")
    public JAXBElement<QueryWeatherByCityNameResponse> createQueryWeatherByCityNameResponse(QueryWeatherByCityNameResponse value) {
        return new JAXBElement<QueryWeatherByCityNameResponse>(_QueryWeatherByCityNameResponse_QNAME, QueryWeatherByCityNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryWeatherByCityName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.Service.web.cx.com/", name = "queryWeatherByCityName")
    public JAXBElement<QueryWeatherByCityName> createQueryWeatherByCityName(QueryWeatherByCityName value) {
        return new JAXBElement<QueryWeatherByCityName>(_QueryWeatherByCityName_QNAME, QueryWeatherByCityName.class, null, value);
    }

}
