
package com.my.user1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "UserService", targetNamespace = "http://service.my.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface UserService {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayLove", targetNamespace = "http://service.my.com/", className = "com.my.user1.SayLove")
    @ResponseWrapper(localName = "sayLoveResponse", targetNamespace = "http://service.my.com/", className = "com.my.user1.SayLoveResponse")
    public String sayLove(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}
