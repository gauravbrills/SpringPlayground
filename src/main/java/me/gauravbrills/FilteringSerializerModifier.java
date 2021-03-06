/*
 * FilteringSerializerModifier.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import lombok.extern.slf4j.Slf4j;
import me.gauravbrills.accesscontrol.ExcludedField;

/**
 * The Class FilteringSerializerModifier. the custom modifier
 */
@Component

/** The Constant LOG. */
@Slf4j
public class FilteringSerializerModifier extends BeanSerializerModifier {

	/** The role based filter service. */
	private @Autowired RoleBasedFilterService roleBasedFilterService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fasterxml.jackson.databind.ser.BeanSerializerModifier#
	 * changeProperties(com.fasterxml.jackson.databind. SerializationConfig,
	 * com.fasterxml.jackson.databind.BeanDescription, java.util.List)
	 */
	@Override
	public List<BeanPropertyWriter> changeProperties(final SerializationConfig config, final BeanDescription beanDesc,
			final List<BeanPropertyWriter> beanProperties) {
		final String beanClass = beanDesc.getBeanClass().getSimpleName();

		final List<ExcludedField> filters = roleBasedFilterService.getFieldSetBasedOnRoleAndEntity(beanClass);

		if (filters.isEmpty()) {
			return beanProperties;
		}

		LOG.info("filter applied {} {}", beanDesc.getBeanClass().getSimpleName(), filters);

		return filters.stream()
				.flatMap(exclusion -> beanProperties.stream().//
						filter(property -> !exclusion.getFieldName().equals(property.getName())).collect(Collectors.toList()).stream())
				.collect(Collectors.toList());
	}

}
