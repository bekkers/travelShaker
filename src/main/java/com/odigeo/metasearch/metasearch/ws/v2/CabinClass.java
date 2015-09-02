
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour cabinClass.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="cabinClass"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="BUSINESS"/&gt;
 *     &lt;enumeration value="FIRST"/&gt;
 *     &lt;enumeration value="TOURIST"/&gt;
 *     &lt;enumeration value="PREMIUM_ECONOMY"/&gt;
 *     &lt;enumeration value="ECONOMIC_DISCOUNTED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "cabinClass")
@XmlEnum
public enum CabinClass {

    BUSINESS,
    FIRST,
    TOURIST,
    PREMIUM_ECONOMY,
    ECONOMIC_DISCOUNTED;

    public static CabinClass fromValue(String v) {
        return valueOf(v);
    }

    public String value() {
        return name();
    }

}
