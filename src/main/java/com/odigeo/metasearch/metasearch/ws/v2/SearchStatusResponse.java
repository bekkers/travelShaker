
package com.odigeo.metasearch.metasearch.ws.v2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour searchStatusResponse complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="searchStatusResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://metasearch.odigeo.com/metasearch/ws/v2/}preferencesAwareResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="itineraryResultsPages" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}searchResultsPage" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="legend" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}itinerariesLegend" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchStatusResponse", propOrder = {
    "itineraryResultsPages",
    "legend"
})
public class SearchStatusResponse
    extends PreferencesAwareResponse
{

    protected List<SearchResultsPage> itineraryResultsPages;
    protected ItinerariesLegend legend;

    /**
     * Gets the value of the itineraryResultsPages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itineraryResultsPages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItineraryResultsPages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SearchResultsPage }
     * 
     * 
     */
    public List<SearchResultsPage> getItineraryResultsPages() {
        if (itineraryResultsPages == null) {
            itineraryResultsPages = new ArrayList<SearchResultsPage>();
        }
        return this.itineraryResultsPages;
    }

    /**
     * Obtient la valeur de la propri�t� legend.
     * 
     * @return
     *     possible object is
     *     {@link ItinerariesLegend }
     *     
     */
    public ItinerariesLegend getLegend() {
        return legend;
    }

    /**
     * D�finit la valeur de la propri�t� legend.
     * 
     * @param value
     *     allowed object is
     *     {@link ItinerariesLegend }
     *     
     */
    public void setLegend(ItinerariesLegend value) {
        this.legend = value;
    }

}
