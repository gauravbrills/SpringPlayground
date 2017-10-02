/*
 * ExcludedFieldRepository.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills.accesscontrol;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * The Interface ExcludedFieldRepository.
 *
 * @author grawat
 */
public interface ExcludedFieldRepository extends CrudRepository<ExcludedField, Long> {

	/**
	 * Find by role and entity name.
	 *
	 * @param authority
	 *            the authority
	 * @param entityName
	 *            the entity name
	 * @return the list
	 */
	List<ExcludedField> findByRoleAndEntityName(@Param("role") String authority,
			@Param("entityName") String entityName);

}
