package me.gauravbrills.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
public class Person {
	private @Id @GeneratedValue Long id;
	private final String firstName;
	private final String lastName;

	protected Person() {
		this(null, null);
	}
}
