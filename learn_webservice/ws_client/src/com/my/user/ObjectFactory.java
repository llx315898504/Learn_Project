
package com.my.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.my.user package. 
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

    private final static QName _QueryPerson_QNAME = new QName("http://service.user.my.com/", "queryPerson");
    private final static QName _Addperson_QNAME = new QName("http://service.user.my.com/", "addperson");
    private final static QName _DeletePerson_QNAME = new QName("http://service.user.my.com/", "deletePerson");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://service.user.my.com/", "deletePersonResponse");
    private final static QName _AddpersonResponse_QNAME = new QName("http://service.user.my.com/", "addpersonResponse");
    private final static QName _QueryPersonResponse_QNAME = new QName("http://service.user.my.com/", "queryPersonResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.my.user
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Addperson }
     * 
     */
    public Addperson createAddperson() {
        return new Addperson();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link AddpersonResponse }
     * 
     */
    public AddpersonResponse createAddpersonResponse() {
        return new AddpersonResponse();
    }

    /**
     * Create an instance of {@link QueryPersonResponse }
     * 
     */
    public QueryPersonResponse createQueryPersonResponse() {
        return new QueryPersonResponse();
    }

    /**
     * Create an instance of {@link QueryPerson }
     * 
     */
    public QueryPerson createQueryPerson() {
        return new QueryPerson();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.user.my.com/", name = "queryPerson")
    public JAXBElement<QueryPerson> createQueryPerson(QueryPerson value) {
        return new JAXBElement<QueryPerson>(_QueryPerson_QNAME, QueryPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Addperson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.user.my.com/", name = "addperson")
    public JAXBElement<Addperson> createAddperson(Addperson value) {
        return new JAXBElement<Addperson>(_Addperson_QNAME, Addperson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.user.my.com/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.user.my.com/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddpersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.user.my.com/", name = "addpersonResponse")
    public JAXBElement<AddpersonResponse> createAddpersonResponse(AddpersonResponse value) {
        return new JAXBElement<AddpersonResponse>(_AddpersonResponse_QNAME, AddpersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.user.my.com/", name = "queryPersonResponse")
    public JAXBElement<QueryPersonResponse> createQueryPersonResponse(QueryPersonResponse value) {
        return new JAXBElement<QueryPersonResponse>(_QueryPersonResponse_QNAME, QueryPersonResponse.class, null, value);
    }

}
