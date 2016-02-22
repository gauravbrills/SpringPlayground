/**
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
 * @author grawat
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringBootPlaygroundApplication.class)
@Transactional
@WebAppConfiguration
@DirtiesContext
public abstract class AbstractWebIntegrationTest {
	@Resource
	protected Filter springSecurityFilterChain;
	protected MockMvc mvc;
	@Autowired
	protected WebApplicationContext context;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(context).//
				//addFilters(springSecurityFilterChain)//
				// apply(configurer)
				build();

	}

}
