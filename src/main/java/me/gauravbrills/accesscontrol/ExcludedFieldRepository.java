/**
 * 
 */
package me.gauravbrills.accesscontrol;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author grawat
 *
 */
public interface ExcludedFieldRepository extends CrudRepository<ExcludedField, Long> {

	List<ExcludedField> findByRoleAndEntityName(@Param("role") String authority, @Param("entityName") String entityName);

}
