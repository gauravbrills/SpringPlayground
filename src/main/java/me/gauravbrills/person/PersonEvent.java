package me.gauravbrills.person;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class PersonEvent {
	private @NonNull String firstName;
}
