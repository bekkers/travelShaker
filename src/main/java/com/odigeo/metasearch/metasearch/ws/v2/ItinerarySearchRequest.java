
package com.odigeo.metasearch.metasearch.ws.v2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour itinerarySearchRequest complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="itinerarySearchRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="segmentRequests" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}itinerarySegmentRequest" maxOccurs="3"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="cabinClass" use="required" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}cabinClass" /&gt;
 *       &lt;attribute name="numAdults" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="numChildren" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="numInfants" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="resident" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="directFlightsOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *       &lt;attribute name="mainAirportsOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itinerarySearchRequest", propOrder = {
    "segmentRequests"
})
public class ItinerarySearchRequest {

    @XmlElement(required = true)
    protected List<ItinerarySegmentRequest> segmentRequests;
    @XmlAttribute(name = "cabinClass", required = true)
    protected CabinClass cabinClass;
    @XmlAttribute(name = "numAdults", required = true)
    protected int numAdults;
    @XmlAttribute(name = "numChildren", required = true)
    protected int numChildren;
    @XmlAttribute(name = "numInfants", required = true)
    protected int numInfants;
    @XmlAttribute(name = "resident")
    protected Boolean resident;
    @XmlAttribute(name = "directFlightsOnly")
    protected Boolean directFlightsOnly;
    @XmlAttribute(name = "mainAirportsOnly")
    protected Boolean mainAirportsOnly;

    /**
     * Obtient la valeur de la propri�t� cabinClass.
     * 
     * @return
     *     possible object is
     *     {@link CabinClass }
     *     
     */
    public CabinClass getCabinClass() {
        return cabinClass;
    }

    /**
     * Obtient la valeur de la propri�t� numAdults.
     * 
     */
    public int getNumAdults() {
        return numAdults;
    }

    /**
     * Obtient la valeur de la propri�t� numChildren.
     * 
     */
    public int getNumChildren() {
        return numChildren;
    }

    /**
     * Obtient la valeur de la propri�t� numInfants.
     * 
     */
    public int getNumInfants() {
        return numInfants;
    }

    /**
     * Gets the value of the segmentRequests property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segmentRequests property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegmentRequests().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItinerarySegmentRequest }
     * 
     * 
     */
    public List<ItinerarySegmentRequest> getSegmentRequests() {
        if (segmentRequests == null) {
            segmentRequests = new ArrayList<ItinerarySegmentRequest>();
        }
        return this.segmentRequests;
    }

    /**
     * Obtient la valeur de la propri�t� directFlightsOnly.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDirectFlightsOnly() {
        return directFlightsOnly;
    }

    /**
     * Obtient la valeur de la propri�t� mainAirportsOnly.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMainAirportsOnly() {
        return mainAirportsOnly;
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
     * D�finit la valeur de la propri�t� cabinClass.
     * 
     * @param value
     *     allowed object is
     *     {@link CabinClass }
     *     
     */
    public void setCabinClass(CabinClass value) {
        this.cabinClass = value;
    }

    /**
     * D�finit la valeur de la propri�t� directFlightsOnly.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDirectFlightsOnly(Boolean value) {
        this.directFlightsOnly = value;
    }

    /**
     * D�finit la valeur de la propri�t� mainAirportsOnly.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMainAirportsOnly(Boolean value) {
        this.mainAirportsOnly = value;
    }

    /**
     * D�finit la valeur de la propri�t� numAdults.
     * 
     */
    public void setNumAdults(int value) {
        this.numAdults = value;
    }

    /**
     * D�finit la valeur de la propri�t� numChildren.
     * 
     */
    public void setNumChildren(int value) {
        this.numChildren = value;
    }

    /**
     * D�finit la valeur de la propri�t� numInfants.
     * 
     */
    public void setNumInfants(int value) {
        this.numInfants = value;
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
