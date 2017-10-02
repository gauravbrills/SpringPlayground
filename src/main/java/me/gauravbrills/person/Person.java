/*
 * Person.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.AbstractAggregateRoot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

/**
 * The Class Person.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper=false)
@RequiredArgsConstructor
public class Person extends AbstractAggregateRoot {

	/** The id. */
	private @Id @GeneratedValue Long id;

	/** The first name. */
	private final String firstName;

	/** The last name. */
	private final String lastName;
	

	/**
	 * Instantiates a new person.
	 */
	protected Person() {
		this(null, null);
	}

	public Person logPersonEvent() {
		PersonEvent event = new PersonEvent(this.firstName);
		registerEvent(event);
		return this;

	}
	
	public Person enroll() {
		PersonEnrollEvent event = new PersonEnrollEvent(this.firstName+" "+this.lastName);
		registerEvent(event);
		return this;

	}
}
