
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour search complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="search"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="preferences" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}preferencesRequest"/&gt;
 *         &lt;element name="searchRequest" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}searchRequest"/&gt;
 *         &lt;element name="metasearchEngineCode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "search", propOrder = {
    "preferences",
    "searchRequest",
    "metasearchEngineCode"
})
public class Search {

    @XmlElement(required = true)
    protected PreferencesRequest preferences;
    @XmlElement(required = true)
    protected SearchRequest searchRequest;
    @XmlElement(required = true)
    protected String metasearchEngineCode;

    /**
     * Obtient la valeur de la propri�t� metasearchEngineCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetasearchEngineCode() {
        return metasearchEngineCode;
    }

    /**
     * Obtient la valeur de la propri�t� preferences.
     * 
     * @return
     *     possible object is
     *     {@link PreferencesRequest }
     *     
     */
    public PreferencesRequest getPreferences() {
        return preferences;
    }

    /**
     * Obtient la valeur de la propri�t� searchRequest.
     * 
     * @return
     *     possible object is
     *     {@link SearchRequest }
     *     
     */
    public SearchRequest getSearchRequest() {
        return searchRequest;
    }

    /**
     * D�finit la valeur de la propri�t� metasearchEngineCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetasearchEngineCode(String value) {
        this.metasearchEngineCode = value;
    }

    /**
     * D�finit la valeur de la propri�t� preferences.
     * 
     * @param value
     *     allowed object is
     *     {@link PreferencesRequest }
     *     
     */
    public void setPreferences(PreferencesRequest value) {
        this.preferences = value;
    }

    /**
     * D�finit la valeur de la propri�t� searchRequest.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchRequest }
     *     
     */
    public void setSearchRequest(SearchRequest value) {
        this.searchRequest = value;
    }

}
