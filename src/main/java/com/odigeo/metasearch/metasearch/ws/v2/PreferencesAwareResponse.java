
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour preferencesAwareResponse complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="preferencesAwareResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://metasearch.odigeo.com/metasearch/ws/v2/}baseResponse"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="preferences" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}preferences"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "preferencesAwareResponse", propOrder = {
    "preferences"
})
@XmlSeeAlso({
    SearchStatusResponse.class
})
public class PreferencesAwareResponse
    extends BaseResponse
{

    @XmlElement(required = true)
    protected Preferences preferences;

    /**
     * Obtient la valeur de la propriété preferences.
     * 
     * @return
     *     possible object is
     *     {@link Preferences }
     *     
     */
    public Preferences getPreferences() {
        return preferences;
    }

    /**
     * Définit la valeur de la propriété preferences.
     * 
     * @param value
     *     allowed object is
     *     {@link Preferences }
     *     
     */
    public void setPreferences(Preferences value) {
        this.preferences = value;
    }

}
