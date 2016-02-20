package me.gauravbrills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
@EnableAutoConfiguration
public class JacksonCustomizations {

	private @Autowired FilteringSerializerModifier serializerModifier;

	/*
	 * @Autowired CustomIntrospector customIntrospector;
	 */
	public @Bean Module hfServicesModule() {

		return new HfServicesModule();
	}

	@SuppressWarnings("serial")
	class HfServicesModule extends SimpleModule {
		public HfServicesModule() {
			// setMixInAnnotation(Fund.class, FundMixin.class);
			setSerializerModifier(serializerModifier);

		}

		@Override
		public void setupModule(SetupContext context) {

			super.setupModule(context);
			// switch on if need custom annotation introspector
			// context.appendAnnotationIntrospector(new CustomIntrospector());
		}

		@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
		abstract class FundMixin {

		}

		/*
		 * @JsonFilter(value = "test") static abstract class PropertyFilterMixin
		 * { }
		 */
	}

}
