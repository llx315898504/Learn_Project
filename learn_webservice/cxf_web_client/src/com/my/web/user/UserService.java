package com.my.web.user;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.2
 * 2018-09-02T10:03:07.994+08:00
 * Generated source version: 2.4.2
 * 
 */
@WebService(targetNamespace = "http://service.my.com/", name = "UserService")
@XmlSeeAlso({ObjectFactory.class})
public interface UserService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "sayHate", targetNamespace = "http://service.my.com/", className = "com.my.web.user.SayHate")
    @WebMethod
    @ResponseWrapper(localName = "sayHateResponse", targetNamespace = "http://service.my.com/", className = "com.my.web.user.SayHateResponse")
    public java.lang.String sayHate(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}
