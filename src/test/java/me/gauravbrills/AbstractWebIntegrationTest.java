/*
 * AbstractWebIntegrationTest.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * The Class AbstractWebIntegrationTest.
 *
 * @author grawat
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// @DataJpaTest
public abstract class AbstractWebIntegrationTest {

	/** The spring security filter chain. */
	protected Filter springSecurityFilterChain;
	protected MockMvc mvc;

	/** The context. */
	@Autowired
	protected WebApplicationContext context;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		 this.mvc = MockMvcBuilders.webAppContextSetup(context).//
		// addFilters(springSecurityFilterChain)//
		// apply(configurer)
		 build();

	}

}
