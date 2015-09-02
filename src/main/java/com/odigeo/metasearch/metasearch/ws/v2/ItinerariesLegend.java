
package com.odigeo.metasearch.metasearch.ws.v2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour itinerariesLegend complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="itinerariesLegend"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="carriers" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}carrier" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="locations" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}location" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="collectionEstimatedFees" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}collectionEstimatedFees" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="collectionMethods" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}collectionMethod" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="sectionResults" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}sectionResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="segmentResults" type="{http://metasearch.odigeo.com/metasearch/ws/v2/}segmentResult" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itinerariesLegend", propOrder = {
    "carriers",
    "locations",
    "collectionEstimatedFees",
    "collectionMethods",
    "sectionResults",
    "segmentResults"
})
public class ItinerariesLegend {

    protected List<Carrier> carriers;
    protected List<Location> locations;
    protected List<CollectionEstimatedFees> collectionEstimatedFees;
    protected List<CollectionMethod> collectionMethods;
    protected List<SectionResult> sectionResults;
    protected List<SegmentResult> segmentResults;

    /**
     * Gets the value of the carriers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the carriers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCarriers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Carrier }
     * 
     * 
     */
    public List<Carrier> getCarriers() {
        if (carriers == null) {
            carriers = new ArrayList<Carrier>();
        }
        return this.carriers;
    }

    /**
     * Gets the value of the collectionEstimatedFees property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the collectionEstimatedFees property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCollectionEstimatedFees().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CollectionEstimatedFees }
     * 
     * 
     */
    public List<CollectionEstimatedFees> getCollectionEstimatedFees() {
        if (collectionEstimatedFees == null) {
            collectionEstimatedFees = new ArrayList<CollectionEstimatedFees>();
        }
        return this.collectionEstimatedFees;
    }

    /**
     * Gets the value of the collectionMethods property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the collectionMethods property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCollectionMethods().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CollectionMethod }
     * 
     * 
     */
    public List<CollectionMethod> getCollectionMethods() {
        if (collectionMethods == null) {
            collectionMethods = new ArrayList<CollectionMethod>();
        }
        return this.collectionMethods;
    }

    /**
     * Gets the value of the locations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the locations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Location }
     * 
     * 
     */
    public List<Location> getLocations() {
        if (locations == null) {
            locations = new ArrayList<Location>();
        }
        return this.locations;
    }

    /**
     * Gets the value of the sectionResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sectionResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSectionResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SectionResult }
     * 
     * 
     */
    public List<SectionResult> getSectionResults() {
        if (sectionResults == null) {
            sectionResults = new ArrayList<SectionResult>();
        }
        return this.sectionResults;
    }

    /**
     * Gets the value of the segmentResults property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segmentResults property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegmentResults().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SegmentResult }
     * 
     * 
     */
    public List<SegmentResult> getSegmentResults() {
        if (segmentResults == null) {
            segmentResults = new ArrayList<SegmentResult>();
        }
        return this.segmentResults;
    }

}
