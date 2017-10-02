/*
 * SpringBootPlaygroundApplication.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import javax.annotation.PostConstruct;
import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;
import org.springframework.jms.annotation.EnableJms;

import me.gauravbrills.accesscontrol.ExcludedField;
import me.gauravbrills.accesscontrol.ExcludedFieldRepository;
import me.gauravbrills.person.Person;
import me.gauravbrills.person.PersonRepository;

/**
 * The Class SpringBootPlaygroundApplication.
 */
@SpringBootApplication
@EnableEntityLinks
@EnableJms
public class SpringBootPlaygroundApplication {
	
	/** The curie namespace. */
	public static String CURIE_NAMESPACE = "gauravbrills";
	
	/** The person repository. */
	@Autowired
	private PersonRepository personRepository;
	
	/** The excluded field repository. */
	@Autowired
	private ExcludedFieldRepository excludedFieldRepository;

	/**
	 * Curie provider.
	 *
	 * @return the curie provider
	 */
	public @Bean CurieProvider curieProvider() {
		return new DefaultCurieProvider(CURIE_NAMESPACE, new UriTemplate("/docs/{rel}.html"));
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("TRADE_EB_POST_QUEUE");
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootPlaygroundApplication.class, args);
	}

	/**
	 * Bootstrap.
	 */
	@PostConstruct
	private void bootstrap() {
		personRepository.save(new Person("Gaurav", "Rawat"));
		personRepository.save(new Person("Dead", "Pool"));
		// put some mock stuff 
		excludedFieldRepository.save(new ExcludedField("lastName","ROLE_USER","Person"));
	}
}
