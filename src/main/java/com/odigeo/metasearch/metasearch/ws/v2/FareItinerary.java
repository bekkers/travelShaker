
package com.odigeo.metasearch.metasearch.ws.v2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour fareItinerary complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="fareItinerary"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="price" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}fareItineraryPrice"/&gt;
 *         &lt;element name="clickoutURLParams" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="key" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="resident" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="firstSegmentsIds" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;list itemType="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="secondSegmentsIds"&gt;
 *         &lt;simpleType&gt;
 *           &lt;list itemType="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *       &lt;attribute name="thirdSegmentsIds"&gt;
 *         &lt;simpleType&gt;
 *           &lt;list itemType="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fareItinerary", propOrder = {
    "price",
    "clickoutURLParams"
})
public class FareItinerary {

    @XmlElement(required = true)
    protected FareItineraryPrice price;
    protected String clickoutURLParams;
    @XmlAttribute(name = "key", required = true)
    protected String key;
    @XmlAttribute(name = "resident")
    protected Boolean resident;
    @XmlAttribute(name = "firstSegmentsIds", required = true)
    protected List<Integer> firstSegmentsIds;
    @XmlAttribute(name = "secondSegmentsIds")
    protected List<Integer> secondSegmentsIds;
    @XmlAttribute(name = "thirdSegmentsIds")
    protected List<Integer> thirdSegmentsIds;

    /**
     * Obtient la valeur de la propri�t� clickoutURLParams.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClickoutURLParams() {
        return clickoutURLParams;
    }

    /**
     * Gets the value of the firstSegmentsIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the firstSegmentsIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFirstSegmentsIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getFirstSegmentsIds() {
        if (firstSegmentsIds == null) {
            firstSegmentsIds = new ArrayList<Integer>();
        }
        return this.firstSegmentsIds;
    }

    /**
     * Obtient la valeur de la propri�t� key.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Obtient la valeur de la propri�t� price.
     * 
     * @return
     *     possible object is
     *     {@link FareItineraryPrice }
     *     
     */
    public FareItineraryPrice getPrice() {
        return price;
    }

    /**
     * Gets the value of the secondSegmentsIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the secondSegmentsIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecondSegmentsIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getSecondSegmentsIds() {
        if (secondSegmentsIds == null) {
            secondSegmentsIds = new ArrayList<Integer>();
        }
        return this.secondSegmentsIds;
    }

    /**
     * Gets the value of the thirdSegmentsIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the thirdSegmentsIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getThirdSegmentsIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getThirdSegmentsIds() {
        if (thirdSegmentsIds == null) {
            thirdSegmentsIds = new ArrayList<Integer>();
        }
        return this.thirdSegmentsIds;
    }

    /**
     * Obtient la valeur de la propri�t� resident.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isResident() {
        return resident;
    }

    /**
     * D�finit la valeur de la propri�t� clickoutURLParams.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClickoutURLParams(String value) {
        this.clickoutURLParams = value;
    }

    /**
     * D�finit la valeur de la propri�t� key.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * D�finit la valeur de la propri�t� price.
     * 
     * @param value
     *     allowed object is
     *     {@link FareItineraryPrice }
     *     
     */
    public void setPrice(FareItineraryPrice value) {
        this.price = value;
    }

    /**
     * D�finit la valeur de la propri�t� resident.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResident(Boolean value) {
        this.resident = value;
    }

}
