package me.gauravbrills.accesscontrol;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class ExcludedField {
	private @Id @GeneratedValue Long id;
	private @NonNull String fieldName;
	private @NonNull String role;
	private @NonNull String entityName;
}
