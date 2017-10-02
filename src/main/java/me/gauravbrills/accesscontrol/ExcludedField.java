/*
 * ExcludedField.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills.accesscontrol;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * The Class ExcludedField.
 */
@Entity

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Data

/**
 * Instantiates a new excluded field.
 *
 * @param fieldName the field name
 * @param role the role
 * @param entityName the entity name
 */
@RequiredArgsConstructor

/**
 * Instantiates a new excluded field.
 */
@NoArgsConstructor
public class ExcludedField {
	
	/** The id. */
	private @Id @GeneratedValue Long id;
	
	/** The field name. */
	private @NonNull String fieldName;
	
	/** The role. */
	private @NonNull String role;
	
	/** The entity name. */
	private @NonNull String entityName;
}
