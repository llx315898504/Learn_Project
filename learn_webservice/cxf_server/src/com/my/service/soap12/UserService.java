package com.my.service.soap12;

import javax.jws.WebService;
import javax.xml.ws.BindingType;
@WebService
//Ĭ�������soap�İ汾��soap11�������ʹ��soap12��Ҫʹ��@BindingTypeע�⣬��value����ָ��ʹ��soap�汾
@BindingType(value=javax.xml.ws.soap.SOAPBinding.SOAP12HTTP_BINDING)
public interface UserService {
   public  String  sayLove(String name);
}
