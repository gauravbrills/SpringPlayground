package me.gauravbrills;

import java.util.List;

public interface RoleBasedFilterService {

	List<String> getFieldSetBasedOnRoleAndEntity(String beanClass);

}
