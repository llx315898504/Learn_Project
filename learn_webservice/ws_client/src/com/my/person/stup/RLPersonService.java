
package com.my.person.stup;

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
@WebService(name = "RLPersonService", targetNamespace = "http://service.user.my.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RLPersonService {


    /**
     * 
     * @param id
     */
    @WebMethod
    @RequestWrapper(localName = "deletePerson", targetNamespace = "http://service.user.my.com/", className = "com.my.person.stup.DeletePerson")
    @ResponseWrapper(localName = "deletePersonResponse", targetNamespace = "http://service.user.my.com/", className = "com.my.person.stup.DeletePersonResponse")
    public void deletePerson(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addperson", targetNamespace = "http://service.user.my.com/", className = "com.my.person.stup.Addperson")
    @ResponseWrapper(localName = "addpersonResponse", targetNamespace = "http://service.user.my.com/", className = "com.my.person.stup.AddpersonResponse")
    public void addperson(
        @WebParam(name = "arg0", targetNamespace = "")
        Person arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.my.person.stup.Person>
     */
    @WebMethod
    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "queryPerson", targetNamespace = "http://service.user.my.com/", className = "com.my.person.stup.QueryPerson")
    @ResponseWrapper(localName = "queryPersonResponse", targetNamespace = "http://service.user.my.com/", className = "com.my.person.stup.QueryPersonResponse")
    public List<Person> queryPerson(
        @WebParam(name = "arg0", targetNamespace = "")
        Person arg0);

}
