/*
 * RoleBasedFilterService.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import java.util.List;

import me.gauravbrills.accesscontrol.ExcludedField;

/**
 * The Interface RoleBasedFilterService.
 */
public interface RoleBasedFilterService {

	/**
	 * Gets the field set based on role and entity.
	 *
	 * @param beanClass the bean class
	 * @return the field set based on role and entity
	 */
	List<ExcludedField> getFieldSetBasedOnRoleAndEntity(String beanClass);

}
