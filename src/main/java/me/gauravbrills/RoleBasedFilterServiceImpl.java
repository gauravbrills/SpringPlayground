package me.gauravbrills;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
class RoleBasedFilterServiceImpl implements RoleBasedFilterService {

	@Override
	public List<String> getFieldSetBasedOnRoleAndEntity(String entityName) {
		// 
		return Arrays.asList("lastName");
	}

}
