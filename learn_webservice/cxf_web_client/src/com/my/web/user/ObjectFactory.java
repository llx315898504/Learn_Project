
package com.my.web.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.my.web.user package. 
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

    private final static QName _SayHateResponse_QNAME = new QName("http://service.my.com/", "sayHateResponse");
    private final static QName _SayHate_QNAME = new QName("http://service.my.com/", "sayHate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.my.web.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHateResponse }
     * 
     */
    public SayHateResponse createSayHateResponse() {
        return new SayHateResponse();
    }

    /**
     * Create an instance of {@link SayHate }
     * 
     */
    public SayHate createSayHate() {
        return new SayHate();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.my.com/", name = "sayHateResponse")
    public JAXBElement<SayHateResponse> createSayHateResponse(SayHateResponse value) {
        return new JAXBElement<SayHateResponse>(_SayHateResponse_QNAME, SayHateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.my.com/", name = "sayHate")
    public JAXBElement<SayHate> createSayHate(SayHate value) {
        return new JAXBElement<SayHate>(_SayHate_QNAME, SayHate.class, null, value);
    }

}
