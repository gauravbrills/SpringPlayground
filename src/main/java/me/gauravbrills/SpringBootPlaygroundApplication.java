package me.gauravbrills;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.config.EnableEntityLinks;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;

import me.gauravbrills.accesscontrol.ExcludedField;
import me.gauravbrills.accesscontrol.ExcludedFieldRepository;
import me.gauravbrills.person.Person;
import me.gauravbrills.person.PersonRepository;

@SpringBootApplication
@EnableEntityLinks
public class SpringBootPlaygroundApplication {
	public static String CURIE_NAMESPACE = "gauravbrills";
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private ExcludedFieldRepository excludedFieldRepository;

	public @Bean CurieProvider curieProvider() {
		return new DefaultCurieProvider(CURIE_NAMESPACE, new UriTemplate("/docs/{rel}.html"));
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootPlaygroundApplication.class, args);
	}

	@PostConstruct
	private void bootstrap() {
		personRepository.save(new Person("Gaurav", "Rawat"));
		personRepository.save(new Person("Dead", "Pool"));
		// put some mock stuff 
		excludedFieldRepository.save(new ExcludedField("lastName","ROLE_USER","Person"));
	}
}
