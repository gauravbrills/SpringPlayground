/*
 * RoleBasedFilterServiceImpl.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gauravbrills.accesscontrol.ExcludedField;
import me.gauravbrills.accesscontrol.ExcludedFieldRepository;

/**
 * The Class RoleBasedFilterServiceImpl.
 */
@Service

/** The Constant LOG. */
@Slf4j

/**
 * Instantiates a new role based filter service impl.
 *
 * @param excludedFldRepo the excluded fld repo
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired) )
class RoleBasedFilterServiceImpl implements RoleBasedFilterService {
	
	/** The excluded fld repo. */
	private @NonNull ExcludedFieldRepository excludedFldRepo;

	/* (non-Javadoc)
	 * @see me.gauravbrills.RoleBasedFilterService#getFieldSetBasedOnRoleAndEntity(java.lang.String)
	 */
	@Override
	public List<ExcludedField> getFieldSetBasedOnRoleAndEntity(String entityName) {

		final Optional<Authentication> authentication = getAuthentication();

		return authentication.//
				map(auth -> mapAuthoritiesForClass(auth, entityName)).//
				orElseGet(() -> new ArrayList<>());
	}

	/**
	 * Gets the authentication.
	 *
	 * @return the authentication
	 */
	private Optional<Authentication> getAuthentication() {
		Optional<Authentication> authentication = Optional
				.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		LOG.info("Authenticated User : {}", authentication.get().getPrincipal());
		return authentication;
	}

	/**
	 * Map authorities for class.
	 *
	 * @param auth the auth
	 * @param clazzName the clazz name
	 * @return the list
	 */
	private List<ExcludedField> mapAuthoritiesForClass(final Authentication auth, final String clazzName) {
		int authCount = auth.getAuthorities().size();
		Map<String, List<ExcludedField>> collect = auth.getAuthorities()
				.stream().flatMap(authority -> excludedFldRepo
						.findByRoleAndEntityName(authority.getAuthority(), clazzName).stream())
				.collect(Collectors.groupingBy(ExcludedField::getFieldName));
		return collect.entrySet().stream().filter(e -> e.getValue().size() == authCount).//
				flatMap(e -> e.getValue().stream()).collect(Collectors.toList());
	}
}
