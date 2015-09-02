package com.prochainvol;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsersTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCheckUser() {
		try {
			Users users = Users.getInstance();
			assertEquals(true, users.checkUser("pratt", "corto"));
			assertEquals(false, users.checkUser("zut", "corto"));
		} catch (Exception e) {
			JunitConstants.reportError(e);
		}
	}
}