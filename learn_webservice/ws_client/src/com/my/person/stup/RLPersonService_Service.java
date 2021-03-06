
package com.my.person.stup;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "RLPersonService", targetNamespace = "http://service.user.my.com/", wsdlLocation = "http://192.168.1.101:9999/Person?wsdl")
public class RLPersonService_Service
    extends Service
{

    private final static URL RLPERSONSERVICE_WSDL_LOCATION;
    private final static WebServiceException RLPERSONSERVICE_EXCEPTION;
    private final static QName RLPERSONSERVICE_QNAME = new QName("http://service.user.my.com/", "RLPersonService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.1.101:9999/Person?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        RLPERSONSERVICE_WSDL_LOCATION = url;
        RLPERSONSERVICE_EXCEPTION = e;
    }

    public RLPersonService_Service() {
        super(__getWsdlLocation(), RLPERSONSERVICE_QNAME);
    }

    public RLPersonService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), RLPERSONSERVICE_QNAME, features);
    }

    public RLPersonService_Service(URL wsdlLocation) {
        super(wsdlLocation, RLPERSONSERVICE_QNAME);
    }

    public RLPersonService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, RLPERSONSERVICE_QNAME, features);
    }

    public RLPersonService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public RLPersonService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns RLPersonService
     */
    @WebEndpoint(name = "RLPersonServicePort")
    public RLPersonService getRLPersonServicePort() {
        return super.getPort(new QName("http://service.user.my.com/", "RLPersonServicePort"), RLPersonService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns RLPersonService
     */
    @WebEndpoint(name = "RLPersonServicePort")
    public RLPersonService getRLPersonServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.user.my.com/", "RLPersonServicePort"), RLPersonService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (RLPERSONSERVICE_EXCEPTION!= null) {
            throw RLPERSONSERVICE_EXCEPTION;
        }
        return RLPERSONSERVICE_WSDL_LOCATION;
    }

}
