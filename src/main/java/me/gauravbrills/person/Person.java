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

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * The Class Person.
 */
@Entity

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data

/**
 * Instantiates a new person.
 *
 * @param firstName the first name
 * @param lastName the last name
 */
@RequiredArgsConstructor
public class Person {
	
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
}
