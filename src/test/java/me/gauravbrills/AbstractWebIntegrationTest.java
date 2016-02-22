/*
 * AbstractWebIntegrationTest.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import javax.annotation.Resource;
import javax.servlet.Filter;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * The Class AbstractWebIntegrationTest.
 *
 * @author grawat
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootPlaygroundApplication.class)
@Transactional
@WebAppConfiguration
@DirtiesContext
public abstract class AbstractWebIntegrationTest {
	
	/** The spring security filter chain. */
	@Resource
	protected Filter springSecurityFilterChain;
	
	/** The mvc. */
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
				//addFilters(springSecurityFilterChain)//
				// apply(configurer)
				build();

	}

}
