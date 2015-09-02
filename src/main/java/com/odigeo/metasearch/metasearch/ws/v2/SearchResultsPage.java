
package com.odigeo.metasearch.metasearch.ws.v2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour searchResultsPage complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="searchResultsPage"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="messages" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}messageResponse" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="itineraryResults" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}fareItinerary" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="clickoutURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="brand" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchResultsPage", propOrder = {
    "messages",
    "itineraryResults",
    "clickoutURL"
})
public class SearchResultsPage {

    protected List<MessageResponse> messages;
    protected List<FareItinerary> itineraryResults;
    protected String clickoutURL;
    @XmlAttribute(name = "brand", required = true)
    protected String brand;

    /**
     * Obtient la valeur de la propri�t� brand.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Obtient la valeur de la propri�t� clickoutURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClickoutURL() {
        return clickoutURL;
    }

    /**
     * Gets the value of the itineraryResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itineraryResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItineraryResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FareItinerary }
     * 
     * 
     */
    public List<FareItinerary> getItineraryResults() {
        if (itineraryResults == null) {
            itineraryResults = new ArrayList<FareItinerary>();
        }
        return this.itineraryResults;
    }

    /**
     * Gets the value of the messages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MessageResponse }
     * 
     * 
     */
    public List<MessageResponse> getMessages() {
        if (messages == null) {
            messages = new ArrayList<MessageResponse>();
        }
        return this.messages;
    }

    /**
     * D�finit la valeur de la propri�t� brand.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
    }

    /**
     * D�finit la valeur de la propri�t� clickoutURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClickoutURL(String value) {
        this.clickoutURL = value;
    }

}
