package com.prochainvol.api.provider.odigeo;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.Constants;
import com.prochainvol.ProchainvolConfig;
import com.prochainvol.ProchainvolException;
import com.prochainvol.api.EXECUTOR_TYPE;
import com.prochainvol.api.provider.AbstractAirlineService;
import com.prochainvol.api.provider.PROVIDER;
import com.prochainvol.api.request.RequestParams;
import com.prochainvol.api.response.ReportUnit;
import com.prochainvol.api.response.RequestResult;
import com.prochainvol.api.response.RequestResultUnit;
import com.prochainvol.api.response.Route;

public class OdigeoService extends AbstractAirlineService {

	private static final Logger logger = Logger.getLogger(OdigeoService.class
			.getName());

	private static final String[] fileList = {Constants.ODIGEO_PROPS.getProperty("debugResultFilename")};

	public static InterfaceOdigeoStreamExecutor createExecutor(
			EXECUTOR_TYPE executorType2, boolean isAsync) {
		return createExecutor(fileList, executorType2, isAsync);
	}

	public static InterfaceOdigeoStreamExecutor createExecutor(String file,
			EXECUTOR_TYPE executorType, boolean isAsync) {
		String[] fileList = { file };
		return createExecutor(fileList, executorType, isAsync);
	}

	public static InterfaceOdigeoStreamExecutor createExecutor(String[] fileList,
			EXECUTOR_TYPE executorType, boolean isAsync) {
		logger.trace("fileList[0] = "+fileList[0]+", size = "+fileList.length);
		if (isAsync) {
			return executorType == EXECUTOR_TYPE.DIRECT ? new OdigeoExecutorDirectAsync()
			: (executorType == EXECUTOR_TYPE.BAM ? new OdigeoExecutorBamAsync()
					: new OdigeoExecutorDebugAsync(fileList));
		} else {
			return executorType == EXECUTOR_TYPE.DIRECT ? new OdigeoExecutorDirectSync()
				: (executorType == EXECUTOR_TYPE.BAM ? new OdigeoExecutorBamSync()
						: new OdigeoExecutorDebugSync(fileList));
		}
	}

	private final OdigeoRequestBuilder odigeoRequestBuilder = new OdigeoRequestBuilder();
	private final OdigeoAnswerBuilder odigeoAnswerBuilder;

	private final PROVIDER provider;

	private final InterfaceOdigeoStreamExecutor executor;

	public OdigeoService(ProchainvolConfig config) throws ProchainvolException {
		this.odigeoAnswerBuilder = new OdigeoAnswerBuilder(config);
		this.provider = PROVIDER.ODIGEO;
		EXECUTOR_TYPE executorType = config.getExecutorType();
		this.executor = createExecutor(executorType, config.isAsync());
	}

	public OdigeoService(ProchainvolConfig config, String file)
			throws ProchainvolException {
		this.odigeoAnswerBuilder = new OdigeoAnswerBuilder(config);
		this.provider = PROVIDER.ODIGEO;
		EXECUTOR_TYPE executorType = config.getExecutorType();
		this.executor = createExecutor(file, executorType, config.isAsync());
	}

	public InterfaceOdigeoStreamExecutor getExecutor() {
		return executor;
	}

	public RequestResult service(Map<String, Search> searchList, RequestParams params)
			throws ProchainvolException {
//		logger.trace("search = "
//				+ JsonUtilities.getGsonPretty().toJson(searchList));

		long startTime = System.nanoTime();
		Date startDate = new Date();

		List<SearchStatusResponse> searchStatusResponse = executor
				.execService(searchList);
		long mediumTime = System.nanoTime();

		logger.trace("Executor = " + executor.getClass().getName());
//		logger.trace("searchStatusResponse = "
//				+ JsonUtilities.getGsonPretty().toJson(searchStatusResponse));

		long endTime = System.nanoTime();
		List<RequestResultUnit> resultUnits = odigeoAnswerBuilder.buildProchainvolAnswer(
				searchStatusResponse, params, startDate);
		List<Route> routes = params.getRouteAsList();
		assert(routes.size()==resultUnits.size());
		for (int i = 0; i<routes.size(); i++) {
			resultUnits.get(i).setRoute(routes.get(i));
		}
		RequestResult result = new RequestResult(resultUnits,
				odigeoAnswerBuilder.getConfig());
//		logger.trace("RequestResultUnit = " + result);
		result.setDuréeHttp((mediumTime - startTime) / 1000000); // durées
																			// en
																			// ms
		result.setDuréeAnalyse((endTime - mediumTime) / 1000000);
		return result;
	}

	@Override
	public RequestResult service(RequestParams params)
			throws ProchainvolException {
		logger.trace("params = " + params);
		Map<String, Search> searchList = odigeoRequestBuilder
				.buildRequest(params);
		return service(searchList, params);
	}

	public RequestResultUnit testService(RequestParams params,
			SearchStatusResponse searchStatusResponse)
			throws ProchainvolException {
		// utilisé en test uniquement
		logger.trace("params = " + params);

		Date startDate = new Date();
		long startTime = System.nanoTime();

		logger.trace("RequestReader = " + this.getClass().getName());

		long mediumTime = System.nanoTime();

		RequestResultUnit result = odigeoAnswerBuilder.buildProchainvolAnswer(
				searchStatusResponse, params, startDate);
		long endTime = System.nanoTime();
		ReportUnit rapportRequete = result.getReportUnit();
		rapportRequete.setDuréeHttp((mediumTime - startTime) / 1000000); // durées
																			// en
																			// ms
		rapportRequete.setDuréeAnalyse((endTime - mediumTime) / 1000000);
		return result;
	}

}
