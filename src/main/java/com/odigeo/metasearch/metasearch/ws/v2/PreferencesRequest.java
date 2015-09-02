
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour preferencesRequest complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="preferencesRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="locale" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="userAgent" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="domainCode" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="realUserIP" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preferencesRequest")
public class PreferencesRequest {

    @XmlAttribute(name = "locale", required = true)
    protected String locale;
    @XmlAttribute(name = "userAgent", required = true)
    protected String userAgent;
    @XmlAttribute(name = "domainCode", required = true)
    protected String domainCode;
    @XmlAttribute(name = "realUserIP", required = true)
    protected String realUserIP;

    /**
     * Obtient la valeur de la propri�t� domainCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDomainCode() {
        return domainCode;
    }

    /**
     * Obtient la valeur de la propri�t� locale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Obtient la valeur de la propri�t� realUserIP.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealUserIP() {
        return realUserIP;
    }

    /**
     * Obtient la valeur de la propri�t� userAgent.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * D�finit la valeur de la propri�t� domainCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDomainCode(String value) {
        this.domainCode = value;
    }

    /**
     * D�finit la valeur de la propri�t� locale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * D�finit la valeur de la propri�t� realUserIP.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealUserIP(String value) {
        this.realUserIP = value;
    }

    /**
     * D�finit la valeur de la propri�t� userAgent.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAgent(String value) {
        this.userAgent = value;
    }

}
