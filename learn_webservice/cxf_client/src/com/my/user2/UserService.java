package com.my.user2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2018-09-01T20:49:53.176+08:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://soap12.service.my.com/", name = "UserService")
@XmlSeeAlso({ObjectFactory.class})
public interface UserService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sayLove", targetNamespace = "http://soap12.service.my.com/", className = "com.my.user2.SayLove")
    @WebMethod
    @ResponseWrapper(localName = "sayLoveResponse", targetNamespace = "http://soap12.service.my.com/", className = "com.my.user2.SayLoveResponse")
    public java.lang.String sayLove(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
