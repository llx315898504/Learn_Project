
package com.my.spring.person;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.my.spring.person package. 
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

    private final static QName _AddPerson_QNAME = new QName("http://service.ws.my.com/", "addPerson");
    private final static QName _AddPersonResponse_QNAME = new QName("http://service.ws.my.com/", "addPersonResponse");
    private final static QName _ListPerson_QNAME = new QName("http://service.ws.my.com/", "listPerson");
    private final static QName _ListPersonResponse_QNAME = new QName("http://service.ws.my.com/", "listPersonResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.my.spring.person
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ListPersonResponse }
     * 
     */
    public ListPersonResponse createListPersonResponse() {
        return new ListPersonResponse();
    }

    /**
     * Create an instance of {@link ListPerson }
     * 
     */
    public ListPerson createListPerson() {
        return new ListPerson();
    }

    /**
     * Create an instance of {@link AddPersonResponse }
     * 
     */
    public AddPersonResponse createAddPersonResponse() {
        return new AddPersonResponse();
    }

    /**
     * Create an instance of {@link AddPerson }
     * 
     */
    public AddPerson createAddPerson() {
        return new AddPerson();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.my.com/", name = "addPerson")
    public JAXBElement<AddPerson> createAddPerson(AddPerson value) {
        return new JAXBElement<AddPerson>(_AddPerson_QNAME, AddPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.my.com/", name = "addPersonResponse")
    public JAXBElement<AddPersonResponse> createAddPersonResponse(AddPersonResponse value) {
        return new JAXBElement<AddPersonResponse>(_AddPersonResponse_QNAME, AddPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.my.com/", name = "listPerson")
    public JAXBElement<ListPerson> createListPerson(ListPerson value) {
        return new JAXBElement<ListPerson>(_ListPerson_QNAME, ListPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.my.com/", name = "listPersonResponse")
    public JAXBElement<ListPersonResponse> createListPersonResponse(ListPersonResponse value) {
        return new JAXBElement<ListPersonResponse>(_ListPersonResponse_QNAME, ListPersonResponse.class, null, value);
    }

}
