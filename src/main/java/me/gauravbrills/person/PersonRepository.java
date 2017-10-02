/*
 * PersonRepository.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills.person;

import org.springframework.data.repository.CrudRepository;

/**
 * The Interface PersonRepository.
 */
public interface PersonRepository extends CrudRepository<Person,Long>{

}
