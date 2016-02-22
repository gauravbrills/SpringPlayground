package me.gauravbrills;

import java.util.List;

import me.gauravbrills.accesscontrol.ExcludedField;

public interface RoleBasedFilterService {

	List<ExcludedField> getFieldSetBasedOnRoleAndEntity(String beanClass);

}
