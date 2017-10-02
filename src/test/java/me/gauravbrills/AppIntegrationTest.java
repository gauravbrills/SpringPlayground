/*
 * AppIntegrationTest.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.ResultActions;

import lombok.extern.slf4j.Slf4j;
import me.gauravbrills.person.Person;
import me.gauravbrills.person.PersonRepository;

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

	@Test
	public void useMultipleRepositoriesSaveWithEvents() {
		List<Person> persons = new ArrayList<>();
		persons.add(new Person("Frodo", "Baggins"));
		persons.add(new Person("Bilbo", "Baggins"));

		personRepository.save(persons);

	}

	@Test
	public void testMultiEventProcessing() throws InterruptedException {
		Person person = new Person("Frodo", "Baggins");
		ExecutorService executor = Executors.newFixedThreadPool(2);
		final CountDownLatch finished = new CountDownLatch(4);
		executor.submit(() -> {
			personRepository.save(person.logPersonEvent().enroll());
			finished.countDown();
		});
		executor.submit(() -> {
			personRepository.save(person.enroll());
			finished.countDown();
		});
		executor.submit(() -> {
			personRepository.save(person.logPersonEvent().enroll());
			finished.countDown();
		});
		executor.submit(() -> {
			personRepository.save(person.logPersonEvent().enroll());
			finished.countDown();
		});
		personRepository.save(person.logPersonEvent().enroll());
		finished.await();
	}

	/**
	 * Test persons endpoint.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithMockUser(roles = { "USER" }, username = "ReadConsumer", value = "Reader")
	public void testPersonsEndpoint() throws Exception {
		final ResultActions result = mvc.perform(get("/persons")).andDo(print());
		result.andExpect(status().isOk())//
				.andExpect(jsonPath("$._embedded.gauravbrills:persons", hasSize(2)));
	}

	/**
	 * Test persons endpoint doesnt have last name.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	@WithMockUser(roles = { "USER" }, username = "ReadConsumer", value = "Reader")
	public void testPersonsEndpointDoesntHaveLastName() throws Exception {
		final ResultActions result = mvc.perform(get("/persons")).andDo(print());
		result.andExpect(status().isOk())//
				// .andExpect(content().contentType(MediaTypes.HAL_JSON))//
				.andExpect(jsonPath("$._embedded.gauravbrills:persons[0].lastName").doesNotExist());
	}

}
