
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour collectionMethodFee complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="collectionMethodFee"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="collectionEstimatedFeesId" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="collectionMethodId" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "collectionMethodFee")
public class CollectionMethodFee {

    @XmlAttribute(name = "collectionEstimatedFeesId")
    protected Integer collectionEstimatedFeesId;
    @XmlAttribute(name = "collectionMethodId")
    protected Integer collectionMethodId;

    /**
     * Obtient la valeur de la propri�t� collectionEstimatedFeesId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCollectionEstimatedFeesId() {
        return collectionEstimatedFeesId;
    }

    /**
     * Obtient la valeur de la propri�t� collectionMethodId.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCollectionMethodId() {
        return collectionMethodId;
    }

    /**
     * D�finit la valeur de la propri�t� collectionEstimatedFeesId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCollectionEstimatedFeesId(Integer value) {
        this.collectionEstimatedFeesId = value;
    }

    /**
     * D�finit la valeur de la propri�t� collectionMethodId.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCollectionMethodId(Integer value) {
        this.collectionMethodId = value;
    }

}
