package com.prochainvol.sql.route;

import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.prochainvol.JunitConstants;
import com.prochainvol.ProchainvolConfig;

public class SqlTest {


	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

	}

	@Test
	public void testGetRoute() {
		try {
			ProchainvolConfig config = new ProchainvolConfig();
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void testDebugRoutes() {
		try {


		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}

	@Test
	public void testSGBDRoute() {
		try {
			Routes routes = new SqlRouteReader().load();
			System.out.println("nb routes = "+routes.getAll().size());
			List<String> listBugs = routes.checkRoutes();
			System.out.println("nb bugs = " + listBugs.size());
			Map<String, List<Route>> all = routes.getAll();
			for (String key : listBugs) {
				System.out.println("\n*** "+key+" ***");
				for (Route route : all.get(key)) {
					System.out.println(route);					
				}
			}
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}

	}


}
