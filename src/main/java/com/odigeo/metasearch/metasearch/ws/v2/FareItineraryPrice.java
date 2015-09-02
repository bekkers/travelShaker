
package com.odigeo.metasearch.metasearch.ws.v2;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour fareItineraryPrice complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="fareItineraryPrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="collectionMethodFee" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}collectionMethodFee" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="totalPrice" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fareItineraryPrice", propOrder = {
    "collectionMethodFee"
})
public class FareItineraryPrice {

    protected CollectionMethodFee collectionMethodFee;
    @XmlAttribute(name = "totalPrice", required = true)
    protected BigDecimal totalPrice;

    /**
     * Obtient la valeur de la propri�t� collectionMethodFee.
     * 
     * @return
     *     possible object is
     *     {@link CollectionMethodFee }
     *     
     */
    public CollectionMethodFee getCollectionMethodFee() {
        return collectionMethodFee;
    }

    /**
     * Obtient la valeur de la propri�t� totalPrice.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    /**
     * D�finit la valeur de la propri�t� collectionMethodFee.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionMethodFee }
     *     
     */
    public void setCollectionMethodFee(CollectionMethodFee value) {
        this.collectionMethodFee = value;
    }

    /**
     * D�finit la valeur de la propri�t� totalPrice.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalPrice(BigDecimal value) {
        this.totalPrice = value;
    }

}
