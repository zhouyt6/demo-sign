
package cn.itcast.crm.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.itcast.crm.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ActiveMail_QNAME = new QName("http://service.crm.itcast.cn/", "activeMail");
    private final static QName _ActiveMailResponse_QNAME = new QName("http://service.crm.itcast.cn/", "activeMailResponse");
    private final static QName _AssignCustomers2FixedArea_QNAME = new QName("http://service.crm.itcast.cn/", "assignCustomers2FixedArea");
    private final static QName _AssignCustomers2FixedAreaResponse_QNAME = new QName("http://service.crm.itcast.cn/", "assignCustomers2FixedAreaResponse");
    private final static QName _FindAll_QNAME = new QName("http://service.crm.itcast.cn/", "findAll");
    private final static QName _FindAllResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findAllResponse");
    private final static QName _FindAssociateCustomers_QNAME = new QName("http://service.crm.itcast.cn/", "findAssociateCustomers");
    private final static QName _FindAssociateCustomersResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findAssociateCustomersResponse");
    private final static QName _FindByTelephoneAndPassword_QNAME = new QName("http://service.crm.itcast.cn/", "findByTelephoneAndPassword");
    private final static QName _FindByTelephoneAndPasswordResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findByTelephoneAndPasswordResponse");
    private final static QName _FindCustomerByTelephone_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephone");
    private final static QName _FindCustomerByTelephoneResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findCustomerByTelephoneResponse");
    private final static QName _FindFixedAreaIdByAddress_QNAME = new QName("http://service.crm.itcast.cn/", "findFixedAreaIdByAddress");
    private final static QName _FindFixedAreaIdByAddressResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findFixedAreaIdByAddressResponse");
    private final static QName _FindNOAssociateCustomers_QNAME = new QName("http://service.crm.itcast.cn/", "findNOAssociateCustomers");
    private final static QName _FindNOAssociateCustomersResponse_QNAME = new QName("http://service.crm.itcast.cn/", "findNOAssociateCustomersResponse");
    private final static QName _RegistCust_QNAME = new QName("http://service.crm.itcast.cn/", "registCust");
    private final static QName _RegistCustResponse_QNAME = new QName("http://service.crm.itcast.cn/", "registCustResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.itcast.crm.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ActiveMail }
     * 
     */
    public ActiveMail createActiveMail() {
        return new ActiveMail();
    }

    /**
     * Create an instance of {@link ActiveMailResponse }
     * 
     */
    public ActiveMailResponse createActiveMailResponse() {
        return new ActiveMailResponse();
    }

    /**
     * Create an instance of {@link AssignCustomers2FixedArea }
     * 
     */
    public AssignCustomers2FixedArea createAssignCustomers2FixedArea() {
        return new AssignCustomers2FixedArea();
    }

    /**
     * Create an instance of {@link AssignCustomers2FixedAreaResponse }
     * 
     */
    public AssignCustomers2FixedAreaResponse createAssignCustomers2FixedAreaResponse() {
        return new AssignCustomers2FixedAreaResponse();
    }

    /**
     * Create an instance of {@link FindAll }
     * 
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     * 
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link FindAssociateCustomers }
     * 
     */
    public FindAssociateCustomers createFindAssociateCustomers() {
        return new FindAssociateCustomers();
    }

    /**
     * Create an instance of {@link FindAssociateCustomersResponse }
     * 
     */
    public FindAssociateCustomersResponse createFindAssociateCustomersResponse() {
        return new FindAssociateCustomersResponse();
    }

    /**
     * Create an instance of {@link FindByTelephoneAndPassword }
     * 
     */
    public FindByTelephoneAndPassword createFindByTelephoneAndPassword() {
        return new FindByTelephoneAndPassword();
    }

    /**
     * Create an instance of {@link FindByTelephoneAndPasswordResponse }
     * 
     */
    public FindByTelephoneAndPasswordResponse createFindByTelephoneAndPasswordResponse() {
        return new FindByTelephoneAndPasswordResponse();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephone }
     * 
     */
    public FindCustomerByTelephone createFindCustomerByTelephone() {
        return new FindCustomerByTelephone();
    }

    /**
     * Create an instance of {@link FindCustomerByTelephoneResponse }
     * 
     */
    public FindCustomerByTelephoneResponse createFindCustomerByTelephoneResponse() {
        return new FindCustomerByTelephoneResponse();
    }

    /**
     * Create an instance of {@link FindFixedAreaIdByAddress }
     * 
     */
    public FindFixedAreaIdByAddress createFindFixedAreaIdByAddress() {
        return new FindFixedAreaIdByAddress();
    }

    /**
     * Create an instance of {@link FindFixedAreaIdByAddressResponse }
     * 
     */
    public FindFixedAreaIdByAddressResponse createFindFixedAreaIdByAddressResponse() {
        return new FindFixedAreaIdByAddressResponse();
    }

    /**
     * Create an instance of {@link FindNOAssociateCustomers }
     * 
     */
    public FindNOAssociateCustomers createFindNOAssociateCustomers() {
        return new FindNOAssociateCustomers();
    }

    /**
     * Create an instance of {@link FindNOAssociateCustomersResponse }
     * 
     */
    public FindNOAssociateCustomersResponse createFindNOAssociateCustomersResponse() {
        return new FindNOAssociateCustomersResponse();
    }

    /**
     * Create an instance of {@link RegistCust }
     * 
     */
    public RegistCust createRegistCust() {
        return new RegistCust();
    }

    /**
     * Create an instance of {@link RegistCustResponse }
     * 
     */
    public RegistCustResponse createRegistCustResponse() {
        return new RegistCustResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActiveMail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "activeMail")
    public JAXBElement<ActiveMail> createActiveMail(ActiveMail value) {
        return new JAXBElement<ActiveMail>(_ActiveMail_QNAME, ActiveMail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ActiveMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "activeMailResponse")
    public JAXBElement<ActiveMailResponse> createActiveMailResponse(ActiveMailResponse value) {
        return new JAXBElement<ActiveMailResponse>(_ActiveMailResponse_QNAME, ActiveMailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignCustomers2FixedArea }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "assignCustomers2FixedArea")
    public JAXBElement<AssignCustomers2FixedArea> createAssignCustomers2FixedArea(AssignCustomers2FixedArea value) {
        return new JAXBElement<AssignCustomers2FixedArea>(_AssignCustomers2FixedArea_QNAME, AssignCustomers2FixedArea.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssignCustomers2FixedAreaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "assignCustomers2FixedAreaResponse")
    public JAXBElement<AssignCustomers2FixedAreaResponse> createAssignCustomers2FixedAreaResponse(AssignCustomers2FixedAreaResponse value) {
        return new JAXBElement<AssignCustomers2FixedAreaResponse>(_AssignCustomers2FixedAreaResponse_QNAME, AssignCustomers2FixedAreaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findAllResponse")
    public JAXBElement<FindAllResponse> createFindAllResponse(FindAllResponse value) {
        return new JAXBElement<FindAllResponse>(_FindAllResponse_QNAME, FindAllResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAssociateCustomers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findAssociateCustomers")
    public JAXBElement<FindAssociateCustomers> createFindAssociateCustomers(FindAssociateCustomers value) {
        return new JAXBElement<FindAssociateCustomers>(_FindAssociateCustomers_QNAME, FindAssociateCustomers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAssociateCustomersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findAssociateCustomersResponse")
    public JAXBElement<FindAssociateCustomersResponse> createFindAssociateCustomersResponse(FindAssociateCustomersResponse value) {
        return new JAXBElement<FindAssociateCustomersResponse>(_FindAssociateCustomersResponse_QNAME, FindAssociateCustomersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByTelephoneAndPassword }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findByTelephoneAndPassword")
    public JAXBElement<FindByTelephoneAndPassword> createFindByTelephoneAndPassword(FindByTelephoneAndPassword value) {
        return new JAXBElement<FindByTelephoneAndPassword>(_FindByTelephoneAndPassword_QNAME, FindByTelephoneAndPassword.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByTelephoneAndPasswordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findByTelephoneAndPasswordResponse")
    public JAXBElement<FindByTelephoneAndPasswordResponse> createFindByTelephoneAndPasswordResponse(FindByTelephoneAndPasswordResponse value) {
        return new JAXBElement<FindByTelephoneAndPasswordResponse>(_FindByTelephoneAndPasswordResponse_QNAME, FindByTelephoneAndPasswordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephone }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephone")
    public JAXBElement<FindCustomerByTelephone> createFindCustomerByTelephone(FindCustomerByTelephone value) {
        return new JAXBElement<FindCustomerByTelephone>(_FindCustomerByTelephone_QNAME, FindCustomerByTelephone.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCustomerByTelephoneResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findCustomerByTelephoneResponse")
    public JAXBElement<FindCustomerByTelephoneResponse> createFindCustomerByTelephoneResponse(FindCustomerByTelephoneResponse value) {
        return new JAXBElement<FindCustomerByTelephoneResponse>(_FindCustomerByTelephoneResponse_QNAME, FindCustomerByTelephoneResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindFixedAreaIdByAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findFixedAreaIdByAddress")
    public JAXBElement<FindFixedAreaIdByAddress> createFindFixedAreaIdByAddress(FindFixedAreaIdByAddress value) {
        return new JAXBElement<FindFixedAreaIdByAddress>(_FindFixedAreaIdByAddress_QNAME, FindFixedAreaIdByAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindFixedAreaIdByAddressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findFixedAreaIdByAddressResponse")
    public JAXBElement<FindFixedAreaIdByAddressResponse> createFindFixedAreaIdByAddressResponse(FindFixedAreaIdByAddressResponse value) {
        return new JAXBElement<FindFixedAreaIdByAddressResponse>(_FindFixedAreaIdByAddressResponse_QNAME, FindFixedAreaIdByAddressResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNOAssociateCustomers }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findNOAssociateCustomers")
    public JAXBElement<FindNOAssociateCustomers> createFindNOAssociateCustomers(FindNOAssociateCustomers value) {
        return new JAXBElement<FindNOAssociateCustomers>(_FindNOAssociateCustomers_QNAME, FindNOAssociateCustomers.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindNOAssociateCustomersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "findNOAssociateCustomersResponse")
    public JAXBElement<FindNOAssociateCustomersResponse> createFindNOAssociateCustomersResponse(FindNOAssociateCustomersResponse value) {
        return new JAXBElement<FindNOAssociateCustomersResponse>(_FindNOAssociateCustomersResponse_QNAME, FindNOAssociateCustomersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistCust }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "registCust")
    public JAXBElement<RegistCust> createRegistCust(RegistCust value) {
        return new JAXBElement<RegistCust>(_RegistCust_QNAME, RegistCust.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistCustResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.crm.itcast.cn/", name = "registCustResponse")
    public JAXBElement<RegistCustResponse> createRegistCustResponse(RegistCustResponse value) {
        return new JAXBElement<RegistCustResponse>(_RegistCustResponse_QNAME, RegistCustResponse.class, null, value);
    }

}
