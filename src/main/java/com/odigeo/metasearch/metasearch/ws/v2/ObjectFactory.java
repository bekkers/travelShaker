
package com.odigeo.metasearch.metasearch.ws.v2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.odigeo.metasearch.metasearch.ws.v2 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Search_QNAME = new QName("http://metasearch.odigeo.com/metasearch/ws/v2/", "search");
    private final static QName _SearchResponse_QNAME = new QName("http://metasearch.odigeo.com/metasearch/ws/v2/", "searchResponse");
    private final static QName _SearchStatusResponse_QNAME = new QName("http://metasearch.odigeo.com/metasearch/ws/v2/", "searchStatusResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.odigeo.metasearch.metasearch.ws.v2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BaseResponse }
     * 
     */
    public BaseResponse createBaseResponse() {
        return new BaseResponse();
    }

    /**
     * Create an instance of {@link Carrier }
     * 
     */
    public Carrier createCarrier() {
        return new Carrier();
    }

    /**
     * Create an instance of {@link CofinogaCard }
     * 
     */
    public CofinogaCard createCofinogaCard() {
        return new CofinogaCard();
    }

    /**
     * Create an instance of {@link CollectionEstimatedFees }
     * 
     */
    public CollectionEstimatedFees createCollectionEstimatedFees() {
        return new CollectionEstimatedFees();
    }

    /**
     * Create an instance of {@link CollectionMethod }
     * 
     */
    public CollectionMethod createCollectionMethod() {
        return new CollectionMethod();
    }

    /**
     * Create an instance of {@link CollectionMethodFee }
     * 
     */
    public CollectionMethodFee createCollectionMethodFee() {
        return new CollectionMethodFee();
    }

    /**
     * Create an instance of {@link CollectionMethodPrice }
     * 
     */
    public CollectionMethodPrice createCollectionMethodPrice() {
        return new CollectionMethodPrice();
    }

    /**
     * Create an instance of {@link CreditCard }
     * 
     */
    public CreditCard createCreditCard() {
        return new CreditCard();
    }

    /**
     * Create an instance of {@link FareItinerary }
     * 
     */
    public FareItinerary createFareItinerary() {
        return new FareItinerary();
    }

    /**
     * Create an instance of {@link FareItineraryPrice }
     * 
     */
    public FareItineraryPrice createFareItineraryPrice() {
        return new FareItineraryPrice();
    }

    /**
     * Create an instance of {@link ItinerariesLegend }
     * 
     */
    public ItinerariesLegend createItinerariesLegend() {
        return new ItinerariesLegend();
    }

    /**
     * Create an instance of {@link ItineraryLocationRequest }
     * 
     */
    public ItineraryLocationRequest createItineraryLocationRequest() {
        return new ItineraryLocationRequest();
    }

    /**
     * Create an instance of {@link ItinerarySearchRequest }
     * 
     */
    public ItinerarySearchRequest createItinerarySearchRequest() {
        return new ItinerarySearchRequest();
    }

    /**
     * Create an instance of {@link ItinerarySegmentRequest }
     * 
     */
    public ItinerarySegmentRequest createItinerarySegmentRequest() {
        return new ItinerarySegmentRequest();
    }

    /**
     * Create an instance of {@link Location }
     * 
     */
    public Location createLocation() {
        return new Location();
    }

    /**
     * Create an instance of {@link MessageResponse }
     * 
     */
    public MessageResponse createMessageResponse() {
        return new MessageResponse();
    }

    /**
     * Create an instance of {@link Preferences }
     * 
     */
    public Preferences createPreferences() {
        return new Preferences();
    }

    /**
     * Create an instance of {@link PreferencesAwareResponse }
     * 
     */
    public PreferencesAwareResponse createPreferencesAwareResponse() {
        return new PreferencesAwareResponse();
    }

    /**
     * Create an instance of {@link PreferencesRequest }
     * 
     */
    public PreferencesRequest createPreferencesRequest() {
        return new PreferencesRequest();
    }

    /**
     * Create an instance of {@link Search }
     * 
     */
    public Search createSearch() {
        return new Search();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Search }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://metasearch.odigeo.com/metasearch/ws/v2/", name = "search")
    public JAXBElement<Search> createSearch(Search value) {
        return new JAXBElement<Search>(_Search_QNAME, Search.class, null, value);
    }

    /**
     * Create an instance of {@link SearchRequest }
     * 
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest();
    }

    /**
     * Create an instance of {@link SearchResponse }
     * 
     */
    public SearchResponse createSearchResponse() {
        return new SearchResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://metasearch.odigeo.com/metasearch/ws/v2/", name = "searchResponse")
    public JAXBElement<SearchResponse> createSearchResponse(SearchResponse value) {
        return new JAXBElement<SearchResponse>(_SearchResponse_QNAME, SearchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link SearchResultsPage }
     * 
     */
    public SearchResultsPage createSearchResultsPage() {
        return new SearchResultsPage();
    }

    /**
     * Create an instance of {@link SearchStatusResponse }
     * 
     */
    public SearchStatusResponse createSearchStatusResponse() {
        return new SearchStatusResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://metasearch.odigeo.com/metasearch/ws/v2/", name = "searchStatusResponse")
    public JAXBElement<SearchStatusResponse> createSearchStatusResponse(SearchStatusResponse value) {
        return new JAXBElement<SearchStatusResponse>(_SearchStatusResponse_QNAME, SearchStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link Section }
     * 
     */
    public Section createSection() {
        return new Section();
    }

    /**
     * Create an instance of {@link SectionResult }
     * 
     */
    public SectionResult createSectionResult() {
        return new SectionResult();
    }

    /**
     * Create an instance of {@link Segment }
     * 
     */
    public Segment createSegment() {
        return new Segment();
    }

    /**
     * Create an instance of {@link SegmentResult }
     * 
     */
    public SegmentResult createSegmentResult() {
        return new SegmentResult();
    }

    /**
     * Create an instance of {@link TechnicalStop }
     * 
     */
    public TechnicalStop createTechnicalStop() {
        return new TechnicalStop();
    }

}
