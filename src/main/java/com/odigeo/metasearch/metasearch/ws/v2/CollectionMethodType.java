
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour collectionMethodType.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="collectionMethodType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CREDITCARD"/&gt;
 *     &lt;enumeration value="ELV"/&gt;
 *     &lt;enumeration value="PAYPAL"/&gt;
 *     &lt;enumeration value="SECURE3D"/&gt;
 *     &lt;enumeration value="BANKTRANSFER"/&gt;
 *     &lt;enumeration value="INSTALLMENT"/&gt;
 *     &lt;enumeration value="COFINOGA"/&gt;
 *     &lt;enumeration value="EBANKING"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "collectionMethodType")
@XmlEnum
public enum CollectionMethodType {

    CREDITCARD("CREDITCARD"),
    ELV("ELV"),
    PAYPAL("PAYPAL"),
    @XmlEnumValue("SECURE3D")
    SECURE_3_D("SECURE3D"),
    BANKTRANSFER("BANKTRANSFER"),
    INSTALLMENT("INSTALLMENT"),
    COFINOGA("COFINOGA"),
    EBANKING("EBANKING");
    public static CollectionMethodType fromValue(String v) {
        for (CollectionMethodType c: CollectionMethodType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    private final String value;

    CollectionMethodType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

}
