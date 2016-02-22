/*
 * AppIntegrationTest.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import lombok.extern.slf4j.Slf4j;
import me.gauravbrills.person.Person;
import me.gauravbrills.person.PersonRepository;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** The Constant LOG. */
@Slf4j
public class AppIntegrationTest extends AbstractWebIntegrationTest {

	/** The person repository. */
	@Autowired
	PersonRepository personRepository;

	/**
	 * Use multiple repositories.
	 */
	@Test
	public void useMultipleRepositories() {

		personRepository.save(new Person("Frodo", "Baggins"));
		personRepository.save(new Person("Bilbo", "Baggins"));

		for (Person person : personRepository.findAll()) {
			LOG.info("Hello {} ", person.toString());
		}

	}

	/**
	 * Test persons endpoint.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testPersonsEndpoint() throws Exception {
		final ResultActions result = mvc.perform(get("/persons")).andDo(print());
		result.andExpect(status().isOk())//
				.andExpect(content().contentType(MediaTypes.HAL_JSON))//
				.andExpect(jsonPath("$._embedded.gauravbrills:persons", hasSize(2)));
	}

	/**
	 * Test persons endpoint doesnt have last name.
	 *
	 * @throws Exception the exception
	 */
	@Test
	@WithMockUser(roles={"USER"},username="ReadConsumer",value="Reader")
	public void testPersonsEndpointDoesntHaveLastName() throws Exception {
		final ResultActions result = mvc.perform(get("/persons")).andDo(print());
		result.andExpect(status().isOk())//
				.andExpect(content().contentType(MediaTypes.HAL_JSON))//
				.andExpect(jsonPath("$._embedded.gauravbrills:persons[0].lastName").doesNotExist());
	}

}
