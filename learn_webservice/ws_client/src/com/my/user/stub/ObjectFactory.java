
package com.my.user.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.my.user.stub package. 
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

    private final static QName _SayLoveResponse_QNAME = new QName("http://service.my.com/", "sayLoveResponse");
    private final static QName _SayLove_QNAME = new QName("http://service.my.com/", "sayLove");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.my.user.stub
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayLoveResponse }
     * 
     */
    public SayLoveResponse createSayLoveResponse() {
        return new SayLoveResponse();
    }

    /**
     * Create an instance of {@link SayLove }
     * 
     */
    public SayLove createSayLove() {
        return new SayLove();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayLoveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.my.com/", name = "sayLoveResponse")
    public JAXBElement<SayLoveResponse> createSayLoveResponse(SayLoveResponse value) {
        return new JAXBElement<SayLoveResponse>(_SayLoveResponse_QNAME, SayLoveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayLove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.my.com/", name = "sayLove")
    public JAXBElement<SayLove> createSayLove(SayLove value) {
        return new JAXBElement<SayLove>(_SayLove_QNAME, SayLove.class, null, value);
    }

}
