/*
 * JacksonCustomizations.java
 * 
 * Copyright Gaurav Rawat 
 *
 * @author Gaurav Rawat 2016
 * 
 */
package me.gauravbrills;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * The Class JacksonCustomizations.
 */
@Configuration
@EnableAutoConfiguration
public class JacksonCustomizations {

	/** The serializer modifier. */
	private @Autowired FilteringSerializerModifier serializerModifier;

	/**
	 * Hf services module.
	 *
	 * @return the module
	 */
	/*
	 * @Autowired CustomIntrospector customIntrospector;
	 */
	public @Bean Module hfServicesModule() {

		return new HfServicesModule();
	}

	/**
	 * The Class HfServicesModule.
	 */
	@SuppressWarnings("serial")
	class HfServicesModule extends SimpleModule {
		
		/**
		 * Instantiates a new hf services module.
		 */
		public HfServicesModule() {
			// setMixInAnnotation(Fund.class, FundMixin.class);
			setSerializerModifier(serializerModifier);

		}

		/* (non-Javadoc)
		 * @see com.fasterxml.jackson.databind.module.SimpleModule#setupModule(com.fasterxml.jackson.databind.Module.SetupContext)
		 */
		@Override
		public void setupModule(SetupContext context) {

			super.setupModule(context);
			// switch on if need custom annotation introspector
			// context.appendAnnotationIntrospector(new CustomIntrospector());
		}

		/**
		 * The Class FundMixin.
		 */
		@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
		abstract class FundMixin {

		}

		/*
		 * @JsonFilter(value = "test") static abstract class PropertyFilterMixin
		 * { }
		 */
	}

}
