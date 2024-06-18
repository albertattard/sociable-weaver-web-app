package demo.sw.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Configuration
public class WebMvcCustomConfiguration implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        final LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        localeInterceptor.setParamName("lang");
        return localeInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
    }

    @Bean
    @RequestScope
    public ServletUriComponentsBuilder urlBuilder() {
        return ServletUriComponentsBuilder.fromCurrentRequest();
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean(final MessageSource messageSource) {
        final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource);
        return bean;
    }
}
