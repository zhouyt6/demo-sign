
package cn.itcast.crm.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>findCustomerByTelephoneResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="findCustomerByTelephoneResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="return" type="{http://service.crm.itcast.cn/}customer" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findCustomerByTelephoneResponse", propOrder = {
    "_return"
})
public class FindCustomerByTelephoneResponse {

    @XmlElement(name = "return")
    protected Customer _return;

    /**
     * 获取return属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Customer }
     *     
     */
    public Customer getReturn() {
        return _return;
    }

    /**
     * 设置return属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Customer }
     *     
     */
    public void setReturn(Customer value) {
        this._return = value;
    }

}
