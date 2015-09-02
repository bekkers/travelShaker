
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java pour itinerarySegmentRequest complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="itinerarySegmentRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="departure" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}itineraryLocationRequest"/&gt;
 *         &lt;element name="destination" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}itineraryLocationRequest"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="date" use="required" type="{http://www.w3.org/2001/XMLSchema}date" /&gt;
 *       &lt;attribute name="time" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="timeWindow" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itinerarySegmentRequest", propOrder = {
    "departure",
    "destination"
})
public class ItinerarySegmentRequest {

    @XmlElement(required = true)
    protected ItineraryLocationRequest departure;
    @XmlElement(required = true)
    protected ItineraryLocationRequest destination;
    @XmlAttribute(name = "date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlAttribute(name = "time")
    protected String time;
    @XmlAttribute(name = "timeWindow")
    protected Integer timeWindow;

    /**
     * Obtient la valeur de la propri�t� date.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Obtient la valeur de la propri�t� departure.
     * 
     * @return
     *     possible object is
     *     {@link ItineraryLocationRequest }
     *     
     */
    public ItineraryLocationRequest getDeparture() {
        return departure;
    }

    /**
     * Obtient la valeur de la propri�t� destination.
     * 
     * @return
     *     possible object is
     *     {@link ItineraryLocationRequest }
     *     
     */
    public ItineraryLocationRequest getDestination() {
        return destination;
    }

    /**
     * Obtient la valeur de la propri�t� time.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTime() {
        return time;
    }

    /**
     * Obtient la valeur de la propri�t� timeWindow.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTimeWindow() {
        return timeWindow;
    }

    /**
     * D�finit la valeur de la propri�t� date.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * D�finit la valeur de la propri�t� departure.
     * 
     * @param value
     *     allowed object is
     *     {@link ItineraryLocationRequest }
     *     
     */
    public void setDeparture(ItineraryLocationRequest value) {
        this.departure = value;
    }

    /**
     * D�finit la valeur de la propri�t� destination.
     * 
     * @param value
     *     allowed object is
     *     {@link ItineraryLocationRequest }
     *     
     */
    public void setDestination(ItineraryLocationRequest value) {
        this.destination = value;
    }

    /**
     * D�finit la valeur de la propri�t� time.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTime(String value) {
        this.time = value;
    }

    /**
     * D�finit la valeur de la propri�t� timeWindow.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTimeWindow(Integer value) {
        this.timeWindow = value;
    }

}
