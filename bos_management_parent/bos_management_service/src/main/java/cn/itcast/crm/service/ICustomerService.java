package cn.itcast.crm.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.10
 * 2018-04-10T11:39:43.745+08:00
 * Generated source version: 3.1.10
 * 
 */
@WebService(targetNamespace = "http://service.crm.itcast.cn/", name = "ICustomerService")
@XmlSeeAlso({ObjectFactory.class})
public interface ICustomerService {

    @RequestWrapper(localName = "assignCustomers2FixedArea", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.AssignCustomers2FixedArea")
    @WebMethod
    @ResponseWrapper(localName = "assignCustomers2FixedAreaResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.AssignCustomers2FixedAreaResponse")
    public void assignCustomers2FixedArea(
        @WebParam(name = "arg0", targetNamespace = "")
        java.util.List<java.lang.Integer> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @RequestWrapper(localName = "activeMail", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.ActiveMail")
    @WebMethod
    @ResponseWrapper(localName = "activeMailResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.ActiveMailResponse")
    public void activeMail(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.Integer arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findAssociateCustomers", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindAssociateCustomers")
    @WebMethod
    @ResponseWrapper(localName = "findAssociateCustomersResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindAssociateCustomersResponse")
    public java.util.List<cn.itcast.crm.service.Customer> findAssociateCustomers(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findByTelephoneAndPassword", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindByTelephoneAndPassword")
    @WebMethod
    @ResponseWrapper(localName = "findByTelephoneAndPasswordResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindByTelephoneAndPasswordResponse")
    public cn.itcast.crm.service.Customer findByTelephoneAndPassword(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.String arg1
    );

    @RequestWrapper(localName = "registCust", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.RegistCust")
    @WebMethod
    @ResponseWrapper(localName = "registCustResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.RegistCustResponse")
    public void registCust(
        @WebParam(name = "arg0", targetNamespace = "")
        cn.itcast.crm.service.Customer arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findFixedAreaIdByAddress", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindFixedAreaIdByAddress")
    @WebMethod
    @ResponseWrapper(localName = "findFixedAreaIdByAddressResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindFixedAreaIdByAddressResponse")
    public java.lang.String findFixedAreaIdByAddress(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findNOAssociateCustomers", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindNOAssociateCustomers")
    @WebMethod
    @ResponseWrapper(localName = "findNOAssociateCustomersResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindNOAssociateCustomersResponse")
    public java.util.List<cn.itcast.crm.service.Customer> findNOAssociateCustomers();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindAll")
    @WebMethod
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindAllResponse")
    public java.util.List<cn.itcast.crm.service.Customer> findAll();

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "findCustomerByTelephone", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindCustomerByTelephone")
    @WebMethod
    @ResponseWrapper(localName = "findCustomerByTelephoneResponse", targetNamespace = "http://service.crm.itcast.cn/", className = "cn.itcast.crm.service.FindCustomerByTelephoneResponse")
    public cn.itcast.crm.service.Customer findCustomerByTelephone(
        @WebParam(name = "arg0", targetNamespace = "")
        java.lang.String arg0
    );
}