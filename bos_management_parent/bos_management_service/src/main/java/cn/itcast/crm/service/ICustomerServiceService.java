package cn.itcast.crm.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2018-04-10T11:39:43.783+08:00
 * Generated source version: 3.1.10
 * 
 */
@WebServiceClient(name = "ICustomerServiceService", 
                  wsdlLocation = "http://localhost:8080/crm_management/services/customer?wsdl",
                  targetNamespace = "http://service.crm.itcast.cn/") 
public class ICustomerServiceService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.crm.itcast.cn/", "ICustomerServiceService");
    public final static QName ICustomerServicePort = new QName("http://service.crm.itcast.cn/", "ICustomerServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://localhost:8080/crm_management/services/customer?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(ICustomerServiceService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://localhost:8080/crm_management/services/customer?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public ICustomerServiceService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public ICustomerServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ICustomerServiceService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public ICustomerServiceService(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public ICustomerServiceService(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public ICustomerServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns ICustomerService
     */
    @WebEndpoint(name = "ICustomerServicePort")
    public ICustomerService getICustomerServicePort() {
        return super.getPort(ICustomerServicePort, ICustomerService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ICustomerService
     */
    @WebEndpoint(name = "ICustomerServicePort")
    public ICustomerService getICustomerServicePort(WebServiceFeature... features) {
        return super.getPort(ICustomerServicePort, ICustomerService.class, features);
    }

}
