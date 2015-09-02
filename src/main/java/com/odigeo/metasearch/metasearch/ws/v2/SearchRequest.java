
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour searchRequest complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="searchRequest"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="itinerarySearchRequest" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}itinerarySearchRequest"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="maxSize" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchRequest", propOrder = {
    "itinerarySearchRequest"
})
public class SearchRequest {

    @XmlElement(required = true)
    protected ItinerarySearchRequest itinerarySearchRequest;
    @XmlAttribute(name = "maxSize", required = true)
    protected int maxSize;

    /**
     * Obtient la valeur de la propri�t� itinerarySearchRequest.
     * 
     * @return
     *     possible object is
     *     {@link ItinerarySearchRequest }
     *     
     */
    public ItinerarySearchRequest getItinerarySearchRequest() {
        return itinerarySearchRequest;
    }

    /**
     * Obtient la valeur de la propri�t� maxSize.
     * 
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * D�finit la valeur de la propri�t� itinerarySearchRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link ItinerarySearchRequest }
     *     
     */
    public void setItinerarySearchRequest(ItinerarySearchRequest value) {
        this.itinerarySearchRequest = value;
    }

    /**
     * D�finit la valeur de la propri�t� maxSize.
     * 
     */
    public void setMaxSize(int value) {
        this.maxSize = value;
    }

}
