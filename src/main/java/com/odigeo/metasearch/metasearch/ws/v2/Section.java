
package com.odigeo.metasearch.metasearch.ws.v2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour section complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="section"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="technicalStops" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}technicalStop" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="fromGeoNodeId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="departureDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="departureTerminal" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="toGeoNodeId" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="arrivalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" /&gt;
 *       &lt;attribute name="arrivalTerminal" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="carrierCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="cabinClass" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}cabinClass" /&gt;
 *       &lt;attribute name="operatingCarrierCode" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="aircraft" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="duration" type="{http://www.w3.org/2001/XMLSchema}long" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "section", propOrder = {
    "technicalStops"
})
public class Section {

    protected List<TechnicalStop> technicalStops;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "fromGeoNodeId", required = true)
    protected int fromGeoNodeId;
    @XmlAttribute(name = "departureDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar departureDate;
    @XmlAttribute(name = "departureTerminal")
    protected String departureTerminal;
    @XmlAttribute(name = "toGeoNodeId", required = true)
    protected int toGeoNodeId;
    @XmlAttribute(name = "arrivalDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar arrivalDate;
    @XmlAttribute(name = "arrivalTerminal")
    protected String arrivalTerminal;
    @XmlAttribute(name = "carrierCode")
    protected String carrierCode;
    @XmlAttribute(name = "cabinClass")
    protected CabinClass cabinClass;
    @XmlAttribute(name = "operatingCarrierCode")
    protected String operatingCarrierCode;
    @XmlAttribute(name = "aircraft")
    protected String aircraft;
    @XmlAttribute(name = "duration")
    protected Long duration;

    /**
     * Obtient la valeur de la propri�t� aircraft.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAircraft() {
        return aircraft;
    }

    /**
     * Obtient la valeur de la propri�t� arrivalDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Obtient la valeur de la propri�t� arrivalTerminal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

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
     * Obtient la valeur de la propri�t� carrierCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierCode() {
        return carrierCode;
    }

    /**
     * Obtient la valeur de la propri�t� departureDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepartureDate() {
        return departureDate;
    }

    /**
     * Obtient la valeur de la propri�t� departureTerminal.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartureTerminal() {
        return departureTerminal;
    }

    /**
     * Obtient la valeur de la propri�t� duration.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Obtient la valeur de la propri�t� fromGeoNodeId.
     * 
     */
    public int getFromGeoNodeId() {
        return fromGeoNodeId;
    }

    /**
     * Obtient la valeur de la propri�t� id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Obtient la valeur de la propri�t� operatingCarrierCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperatingCarrierCode() {
        return operatingCarrierCode;
    }

    /**
     * Gets the value of the technicalStops property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the technicalStops property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTechnicalStops().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TechnicalStop }
     * 
     * 
     */
    public List<TechnicalStop> getTechnicalStops() {
        if (technicalStops == null) {
            technicalStops = new ArrayList<TechnicalStop>();
        }
        return this.technicalStops;
    }

    /**
     * Obtient la valeur de la propri�t� toGeoNodeId.
     * 
     */
    public int getToGeoNodeId() {
        return toGeoNodeId;
    }

    /**
     * D�finit la valeur de la propri�t� aircraft.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAircraft(String value) {
        this.aircraft = value;
    }

    /**
     * D�finit la valeur de la propri�t� arrivalDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setArrivalDate(XMLGregorianCalendar value) {
        this.arrivalDate = value;
    }

    /**
     * D�finit la valeur de la propri�t� arrivalTerminal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArrivalTerminal(String value) {
        this.arrivalTerminal = value;
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
     * D�finit la valeur de la propri�t� carrierCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierCode(String value) {
        this.carrierCode = value;
    }

    /**
     * D�finit la valeur de la propri�t� departureDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepartureDate(XMLGregorianCalendar value) {
        this.departureDate = value;
    }

    /**
     * D�finit la valeur de la propri�t� departureTerminal.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartureTerminal(String value) {
        this.departureTerminal = value;
    }

    /**
     * D�finit la valeur de la propri�t� duration.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDuration(Long value) {
        this.duration = value;
    }

    /**
     * D�finit la valeur de la propri�t� fromGeoNodeId.
     * 
     */
    public void setFromGeoNodeId(int value) {
        this.fromGeoNodeId = value;
    }

    /**
     * D�finit la valeur de la propri�t� id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * D�finit la valeur de la propri�t� operatingCarrierCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperatingCarrierCode(String value) {
        this.operatingCarrierCode = value;
    }

    /**
     * D�finit la valeur de la propri�t� toGeoNodeId.
     * 
     */
    public void setToGeoNodeId(int value) {
        this.toGeoNodeId = value;
    }

}
