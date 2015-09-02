
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour searchResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="searchResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="searchStatus" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}searchStatusResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "searchResponse", propOrder = {
    "searchStatus"
})
public class SearchResponse {

    protected SearchStatusResponse searchStatus;

    /**
     * Obtient la valeur de la propriété searchStatus.
     * 
     * @return
     *     possible object is
     *     {@link SearchStatusResponse }
     *     
     */
    public SearchStatusResponse getSearchStatus() {
        return searchStatus;
    }

    /**
     * Définit la valeur de la propriété searchStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchStatusResponse }
     *     
     */
    public void setSearchStatus(SearchStatusResponse value) {
        this.searchStatus = value;
    }

}
