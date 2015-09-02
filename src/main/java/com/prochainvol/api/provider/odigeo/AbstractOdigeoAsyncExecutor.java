package com.prochainvol.api.provider.odigeo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;

import com.odigeo.metasearch.metasearch.ws.v2.Search;
import com.odigeo.metasearch.metasearch.ws.v2.SearchStatusResponse;
import com.prochainvol.ProchainvolException;

public class AbstractOdigeoAsyncExecutor implements InterfaceOdigeoStreamExecutor {

	public class PostCallable implements Callable<SearchStatusResponse> {

		private final Search search;

		public PostCallable(Search search) {
			this.search = search;
		}

		@Override
		public SearchStatusResponse call() throws Exception {
			return operator.execService(search);
		}

	}

	private static final Logger logger = Logger
			.getLogger(AbstractOdigeoAsyncExecutor.class.getName());
	
	protected InterfaceOdigeoExecutor operator;

	private final List<SearchStatusResponse> result = new ArrayList<SearchStatusResponse>();
	private final List<Exception> exceptions = new ArrayList<Exception>();

	private int counter;

	@Override
	public List<SearchStatusResponse> execService(Map<String, Search> searchList)
			throws ProchainvolException {
		ExecutorService executor = Executors.newFixedThreadPool(searchList.size());
		List<FutureTask<SearchStatusResponse>> tasks = new ArrayList<FutureTask<SearchStatusResponse>>();
		for (Map.Entry<String, Search> entry : searchList.entrySet()) {
			FutureTask<SearchStatusResponse> futureTask = new FutureTask<SearchStatusResponse>(
					new PostCallable(entry.getValue()));
			executor.execute(futureTask);
			tasks.add(futureTask);
		}
		List<SearchStatusResponse> result = new ArrayList<SearchStatusResponse>();
		while (true) {
			boolean isDone = true;
			for (FutureTask<SearchStatusResponse> task : tasks) {
				if (!task.isDone()) {
					isDone = false;
					break;
				}
			}
			if (isDone) {
				System.out.println("Done");
				// shut down executor service
				executor.shutdown();
				break;
			}
			try {
				for (FutureTask<SearchStatusResponse> task : tasks) {
					if (!task.isDone()) {
						// wait indefinitely for future task to complete
						SearchStatusResponse response = task.get();
						putResponse(response);
						break;
					}
				}
			} catch (InterruptedException | ExecutionException e) {
				logger.error(e);
				exceptions.add(e);
				break;
			}
		}
		if (exceptions.size()>0) {
			String mess = "Execution interrompue";
			logger.error(mess, exceptions.get(0));
			throw new ProchainvolException(mess);
		}
		return result;
	}

	private synchronized void putResponse(SearchStatusResponse res) {
		try {
			result.add(res);
		} catch (Exception e) {
			logger.error(e);
			exceptions.add(e);
		} finally {
			counter = counter - 1;
		}
	}


}
