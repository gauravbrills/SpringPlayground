package me.gauravbrills.person;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PersonEnrollEvent {
	private @NonNull String fullName;
}
