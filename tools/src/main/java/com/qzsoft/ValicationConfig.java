package com.qzsoft;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.qzsoft.common.inner.ConfigUtil1;

import oth.common.anotation.Test;

@Configuration
public class ValicationConfig {
	public ResourceBundleMessageSource getMessageSource() throws Exception {
		String local = ConfigUtil1.getPropertyValue("i18n.local");
        ResourceBundleMessageSource rbms = new ResourceBundleMessageSource();
        rbms.setDefaultEncoding("UTF-8");
        rbms.setBasenames("i18n/validation/ValidationMessages_"+local);
        return rbms;
    }

    @Bean
    @Test
    public Validator validator() throws Exception {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(getMessageSource());
        return validator;
    }
}
