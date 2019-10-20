package com.my.service.soap12;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
@WebService
//默认情况下soap的版本是soap11，如果想使用soap12需要使用@BindingType注解，用value属性指定使用soap版本
@BindingType(value=javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface UserService {
   public  String  sayLove(String name);
}
