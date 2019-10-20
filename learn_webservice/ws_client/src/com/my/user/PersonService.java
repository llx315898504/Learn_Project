
package com.my.user;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PersonService", targetNamespace = "http://service.user.my.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PersonService {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "deletePerson", targetNamespace = "http://service.user.my.com/", className = "com.my.user.DeletePerson")
    @ResponseWrapper(localName = "deletePersonResponse", targetNamespace = "http://service.user.my.com/", className = "com.my.user.DeletePersonResponse")
    public void deletePerson(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addperson", targetNamespace = "http://service.user.my.com/", className = "com.my.user.Addperson")
    @ResponseWrapper(localName = "addpersonResponse", targetNamespace = "http://service.user.my.com/", className = "com.my.user.AddpersonResponse")
    public void addperson(
        @WebParam(name = "arg0", targetNamespace = "")
        Person arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.my.user.Person>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "queryPerson", targetNamespace = "http://service.user.my.com/", className = "com.my.user.QueryPerson")
    @ResponseWrapper(localName = "queryPersonResponse", targetNamespace = "http://service.user.my.com/", className = "com.my.user.QueryPersonResponse")
    public List<Person> queryPerson(
        @WebParam(name = "arg0", targetNamespace = "")
        Person arg0);

}
