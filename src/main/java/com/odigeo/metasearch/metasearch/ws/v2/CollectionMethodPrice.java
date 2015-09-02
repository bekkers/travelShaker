
package com.odigeo.metasearch.metasearch.ws.v2;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour collectionMethodPrice complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="collectionMethodPrice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="collectionMethodId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="price" use="required" type="{http://www.w3.org/2001/XMLSchema}decimal" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "collectionMethodPrice")
public class CollectionMethodPrice {

    @XmlAttribute(name = "collectionMethodId", required = true)
    protected int collectionMethodId;
    @XmlAttribute(name = "price", required = true)
    protected BigDecimal price;

    /**
     * Obtient la valeur de la propri�t� collectionMethodId.
     * 
     */
    public int getCollectionMethodId() {
        return collectionMethodId;
    }

    /**
     * Obtient la valeur de la propri�t� price.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * D�finit la valeur de la propri�t� collectionMethodId.
     * 
     */
    public void setCollectionMethodId(int value) {
        this.collectionMethodId = value;
    }

    /**
     * D�finit la valeur de la propri�t� price.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

}
