
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour collectionMethod complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="collectionMethod"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="creditCard" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}creditCard" minOccurs="0"/&gt;
 *         &lt;element name="cofinogaCard" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}cofinogaCard" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="type" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}collectionMethodType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "collectionMethod", propOrder = {
    "creditCard",
    "cofinogaCard"
})
public class CollectionMethod {

    protected CreditCard creditCard;
    protected CofinogaCard cofinogaCard;
    @XmlAttribute(name = "id", required = true)
    protected int id;
    @XmlAttribute(name = "type")
    protected CollectionMethodType type;

    /**
     * Obtient la valeur de la propri�t� cofinogaCard.
     * 
     * @return
     *     possible object is
     *     {@link CofinogaCard }
     *     
     */
    public CofinogaCard getCofinogaCard() {
        return cofinogaCard;
    }

    /**
     * Obtient la valeur de la propri�t� creditCard.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Obtient la valeur de la propri�t� id.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Obtient la valeur de la propri�t� type.
     * 
     * @return
     *     possible object is
     *     {@link CollectionMethodType }
     *     
     */
    public CollectionMethodType getType() {
        return type;
    }

    /**
     * D�finit la valeur de la propri�t� cofinogaCard.
     * 
     * @param value
     *     allowed object is
     *     {@link CofinogaCard }
     *     
     */
    public void setCofinogaCard(CofinogaCard value) {
        this.cofinogaCard = value;
    }

    /**
     * D�finit la valeur de la propri�t� creditCard.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCreditCard(CreditCard value) {
        this.creditCard = value;
    }

    /**
     * D�finit la valeur de la propri�t� id.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * D�finit la valeur de la propri�t� type.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionMethodType }
     *     
     */
    public void setType(CollectionMethodType value) {
        this.type = value;
    }

}
