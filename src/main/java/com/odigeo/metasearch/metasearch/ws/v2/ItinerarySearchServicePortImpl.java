
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package com.odigeo.metasearch.metasearch.ws.v2;

import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

/**
 * This class was generated by Apache CXF 3.1.0
 * 2015-08-11T21:09:02.961+02:00
 * Generated source version: 3.1.0
 * 
 */

@javax.jws.WebService(
                      serviceName = "ItinerarySearch",
                      portName = "ItinerarySearchServicePort",
                      targetNamespace = "http://metasearch.odigeo.com/metasearch/ws/v2/",
                      wsdlLocation = "http://www.prochainvol.com:8080/bam/Bam?url=http://metasearch.odigeo.com/metasearch/ws/v2/ItinerarySearch?wsdl",
                      endpointInterface = "com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchService")
                      
public class ItinerarySearchServicePortImpl implements ItinerarySearchService {

    private static final Logger LOG = Logger.getLogger(ItinerarySearchServicePortImpl.class.getName());

    /* (non-Javadoc)
     * @see com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchService#search(com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest  preferences ,)com.odigeo.metasearch.metasearch.ws.v2.SearchRequest  searchRequest ,)java.lang.String  metasearchEngineCode )*
     */
    public com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse search(com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest preferences,com.odigeo.metasearch.metasearch.ws.v2.SearchRequest searchRequest,java.lang.String metasearchEngineCode) { 
        LOG.info("Executing operation search");
        System.out.println(preferences);
        System.out.println(searchRequest);
        System.out.println(metasearchEngineCode);
        try {
            com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse _return = new com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse();
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.MessageResponse> _returnMessages = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.MessageResponse>();
            com.odigeo.metasearch.metasearch.ws.v2.MessageResponse _returnMessagesVal1 = new com.odigeo.metasearch.metasearch.ws.v2.MessageResponse();
            _returnMessagesVal1.setCode("Code273747485");
            _returnMessagesVal1.setDescription("Description-692612099");
            _returnMessages.add(_returnMessagesVal1);
            _return.getMessages().addAll(_returnMessages);
            com.odigeo.metasearch.metasearch.ws.v2.Preferences _returnPreferences = new com.odigeo.metasearch.metasearch.ws.v2.Preferences();
            _returnPreferences.setCurrency("Currency-1732457393");
            _returnPreferences.setLocale("Locale-1923647283");
            _return.setPreferences(_returnPreferences);
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.SearchResultsPage> _returnItineraryResultsPages = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.SearchResultsPage>();
            com.odigeo.metasearch.metasearch.ws.v2.SearchResultsPage _returnItineraryResultsPagesVal1 = new com.odigeo.metasearch.metasearch.ws.v2.SearchResultsPage();
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.MessageResponse> _returnItineraryResultsPagesVal1Messages = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.MessageResponse>();
            _returnItineraryResultsPagesVal1.getMessages().addAll(_returnItineraryResultsPagesVal1Messages);
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.FareItinerary> _returnItineraryResultsPagesVal1ItineraryResults = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.FareItinerary>();
            _returnItineraryResultsPagesVal1.getItineraryResults().addAll(_returnItineraryResultsPagesVal1ItineraryResults);
            _returnItineraryResultsPagesVal1.setClickoutURL("ClickoutURL1435871007");
            _returnItineraryResultsPagesVal1.setBrand("Brand983614155");
            _returnItineraryResultsPages.add(_returnItineraryResultsPagesVal1);
            _return.getItineraryResultsPages().addAll(_returnItineraryResultsPages);
            com.odigeo.metasearch.metasearch.ws.v2.ItinerariesLegend _returnLegend = new com.odigeo.metasearch.metasearch.ws.v2.ItinerariesLegend();
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.Carrier> _returnLegendCarriers = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.Carrier>();
            com.odigeo.metasearch.metasearch.ws.v2.Carrier _returnLegendCarriersVal1 = new com.odigeo.metasearch.metasearch.ws.v2.Carrier();
            _returnLegendCarriersVal1.setCode("Code1887658048");
            _returnLegendCarriersVal1.setName("Name76122437");
            _returnLegendCarriers.add(_returnLegendCarriersVal1);
            _returnLegend.getCarriers().addAll(_returnLegendCarriers);
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.Location> _returnLegendLocations = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.Location>();
            com.odigeo.metasearch.metasearch.ws.v2.Location _returnLegendLocationsVal1 = new com.odigeo.metasearch.metasearch.ws.v2.Location();
            _returnLegendLocationsVal1.setGeoNodeId(-1899977184);
            _returnLegendLocationsVal1.setCityName("CityName291359103");
            _returnLegendLocationsVal1.setName("Name318109841");
            _returnLegendLocationsVal1.setType("Type249954899");
            _returnLegendLocationsVal1.setIataCode("IataCode-1080286665");
            _returnLegendLocationsVal1.setCityIataCode("CityIataCode-1634916949");
            _returnLegendLocationsVal1.setCountryCode("CountryCode-1968543543");
            _returnLegendLocationsVal1.setCountryName("CountryName268143948");
            _returnLegendLocations.add(_returnLegendLocationsVal1);
            _returnLegend.getLocations().addAll(_returnLegendLocations);
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.CollectionEstimatedFees> _returnLegendCollectionEstimatedFees = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.CollectionEstimatedFees>();
            com.odigeo.metasearch.metasearch.ws.v2.CollectionEstimatedFees _returnLegendCollectionEstimatedFeesVal1 = new com.odigeo.metasearch.metasearch.ws.v2.CollectionEstimatedFees();
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.CollectionMethodPrice> _returnLegendCollectionEstimatedFeesVal1Fees = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.CollectionMethodPrice>();
            _returnLegendCollectionEstimatedFeesVal1.getFees().addAll(_returnLegendCollectionEstimatedFeesVal1Fees);
            _returnLegendCollectionEstimatedFeesVal1.setId(1331083619);
            _returnLegendCollectionEstimatedFees.add(_returnLegendCollectionEstimatedFeesVal1);
            _returnLegend.getCollectionEstimatedFees().addAll(_returnLegendCollectionEstimatedFees);
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.CollectionMethod> _returnLegendCollectionMethods = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.CollectionMethod>();
            com.odigeo.metasearch.metasearch.ws.v2.CollectionMethod _returnLegendCollectionMethodsVal1 = new com.odigeo.metasearch.metasearch.ws.v2.CollectionMethod();
            com.odigeo.metasearch.metasearch.ws.v2.CreditCard _returnLegendCollectionMethodsVal1CreditCard = new com.odigeo.metasearch.metasearch.ws.v2.CreditCard();
            _returnLegendCollectionMethodsVal1CreditCard.setCode("Code1798382218");
            _returnLegendCollectionMethodsVal1CreditCard.setName("Name-1796273295");
            _returnLegendCollectionMethodsVal1.setCreditCard(_returnLegendCollectionMethodsVal1CreditCard);
            com.odigeo.metasearch.metasearch.ws.v2.CofinogaCard _returnLegendCollectionMethodsVal1CofinogaCard = new com.odigeo.metasearch.metasearch.ws.v2.CofinogaCard();
            _returnLegendCollectionMethodsVal1CofinogaCard.setCode("Code-1675045670");
            _returnLegendCollectionMethodsVal1.setCofinogaCard(_returnLegendCollectionMethodsVal1CofinogaCard);
            _returnLegendCollectionMethodsVal1.setId(-670282746);
            com.odigeo.metasearch.metasearch.ws.v2.CollectionMethodType _returnLegendCollectionMethodsVal1Type = com.odigeo.metasearch.metasearch.ws.v2.CollectionMethodType.EBANKING;
            _returnLegendCollectionMethodsVal1.setType(_returnLegendCollectionMethodsVal1Type);
            _returnLegendCollectionMethods.add(_returnLegendCollectionMethodsVal1);
            _returnLegend.getCollectionMethods().addAll(_returnLegendCollectionMethods);
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.SectionResult> _returnLegendSectionResults = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.SectionResult>();
            com.odigeo.metasearch.metasearch.ws.v2.SectionResult _returnLegendSectionResultsVal1 = new com.odigeo.metasearch.metasearch.ws.v2.SectionResult();
            com.odigeo.metasearch.metasearch.ws.v2.Section _returnLegendSectionResultsVal1Section = new com.odigeo.metasearch.metasearch.ws.v2.Section();
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.TechnicalStop> _returnLegendSectionResultsVal1SectionTechnicalStops = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.TechnicalStop>();
            _returnLegendSectionResultsVal1Section.getTechnicalStops().addAll(_returnLegendSectionResultsVal1SectionTechnicalStops);
            _returnLegendSectionResultsVal1Section.setId("Id-206426977");
            _returnLegendSectionResultsVal1Section.setFromGeoNodeId(-631906817);
            _returnLegendSectionResultsVal1Section.setDepartureDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar("2015-08-11T21:09:02.977+02:00"));
            _returnLegendSectionResultsVal1Section.setDepartureTerminal("DepartureTerminal-799376492");
            _returnLegendSectionResultsVal1Section.setToGeoNodeId(-726858697);
            _returnLegendSectionResultsVal1Section.setArrivalDate(javax.xml.datatype.DatatypeFactory.newInstance().newXMLGregorianCalendar("2015-08-11T21:09:02.978+02:00"));
            _returnLegendSectionResultsVal1Section.setArrivalTerminal("ArrivalTerminal-606271024");
            _returnLegendSectionResultsVal1Section.setCarrierCode("CarrierCode-235180711");
            com.odigeo.metasearch.metasearch.ws.v2.CabinClass _returnLegendSectionResultsVal1SectionCabinClass = com.odigeo.metasearch.metasearch.ws.v2.CabinClass.TOURIST;
            _returnLegendSectionResultsVal1Section.setCabinClass(_returnLegendSectionResultsVal1SectionCabinClass);
            _returnLegendSectionResultsVal1Section.setOperatingCarrierCode("OperatingCarrierCode-1378689388");
            _returnLegendSectionResultsVal1Section.setAircraft("Aircraft-1833010437");
            _returnLegendSectionResultsVal1Section.setDuration(Long.valueOf(2010009130046073750l));
            _returnLegendSectionResultsVal1.setSection(_returnLegendSectionResultsVal1Section);
            _returnLegendSectionResultsVal1.setId(-936685370);
            _returnLegendSectionResults.add(_returnLegendSectionResultsVal1);
            _returnLegend.getSectionResults().addAll(_returnLegendSectionResults);
            java.util.List<com.odigeo.metasearch.metasearch.ws.v2.SegmentResult> _returnLegendSegmentResults = new java.util.ArrayList<com.odigeo.metasearch.metasearch.ws.v2.SegmentResult>();
            com.odigeo.metasearch.metasearch.ws.v2.SegmentResult _returnLegendSegmentResultsVal1 = new com.odigeo.metasearch.metasearch.ws.v2.SegmentResult();
            com.odigeo.metasearch.metasearch.ws.v2.Segment _returnLegendSegmentResultsVal1Segment = new com.odigeo.metasearch.metasearch.ws.v2.Segment();
            java.util.List<java.lang.Integer> _returnLegendSegmentResultsVal1SegmentSectionIds = new java.util.ArrayList<java.lang.Integer>();
            _returnLegendSegmentResultsVal1Segment.getSectionIds().addAll(_returnLegendSegmentResultsVal1SegmentSectionIds);
            _returnLegendSegmentResultsVal1Segment.setKey("Key-766600990");
            _returnLegendSegmentResultsVal1Segment.setCarrierCode("CarrierCode1205732491");
            _returnLegendSegmentResultsVal1Segment.setDuration(Long.valueOf(5971392832168702264l));
            _returnLegendSegmentResultsVal1Segment.setSeats(Integer.valueOf(785773738));
            _returnLegendSegmentResultsVal1.setSegment(_returnLegendSegmentResultsVal1Segment);
            _returnLegendSegmentResultsVal1.setId(893623953);
            _returnLegendSegmentResults.add(_returnLegendSegmentResultsVal1);
            _returnLegend.getSegmentResults().addAll(_returnLegendSegmentResults);
            _return.setLegend(_returnLegend);
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchService#searchAsync(com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest  preferences ,)com.odigeo.metasearch.metasearch.ws.v2.SearchRequest  searchRequest ,)java.lang.String  metasearchEngineCode )*
     */
    public Response<com.odigeo.metasearch.metasearch.ws.v2.SearchResponse> searchAsync(com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest preferences,com.odigeo.metasearch.metasearch.ws.v2.SearchRequest searchRequest,java.lang.String metasearchEngineCode) { 
       return null;
       /* not called */
    }

    /* (non-Javadoc)
     * @see com.odigeo.metasearch.metasearch.ws.v2.ItinerarySearchService#searchAsync(com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest  preferences ,)com.odigeo.metasearch.metasearch.ws.v2.SearchRequest  searchRequest ,)java.lang.String  metasearchEngineCode ,)AsyncHandler<com.odigeo.metasearch.metasearch.ws.v2.SearchResponse>  asyncHandler )*
     */
    public Future<?> searchAsync(com.odigeo.metasearch.metasearch.ws.v2.PreferencesRequest preferences,com.odigeo.metasearch.metasearch.ws.v2.SearchRequest searchRequest,java.lang.String metasearchEngineCode,AsyncHandler<com.odigeo.metasearch.metasearch.ws.v2.SearchResponse> asyncHandler) { 
       return null;
       /* not called */
    }

}