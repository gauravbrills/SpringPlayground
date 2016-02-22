package me.gauravbrills.accesscontrol;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ExcludedField {
	private @Id @GeneratedValue Long id;
	private @NonNull String fieldName;
	private @NonNull String role;
	private @NonNull String entityName;
}
