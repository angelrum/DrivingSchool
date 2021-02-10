package ru.project.drivingschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import ru.project.drivingschool.controller.json.JacksonObjectMapper;

import java.nio.charset.Charset;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.project.drivingschool.**.repository"})
public class DrivingSchoolApplication extends SpringBootServletInitializer {
    private static final String DEF_ENCODING = "UTF-8";

    public static void main(String[] args) {
        SpringApplication.run(DrivingSchoolApplication.class, args);
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(JacksonObjectMapper.getMapper());
        return converter;
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
        messageConverter.setSupportedMediaTypes(List.of(
                new MediaType(MediaType.TEXT_PLAIN, Charset.forName(DEF_ENCODING)),
                new MediaType(MediaType.TEXT_HTML, Charset.forName(DEF_ENCODING))));
        return messageConverter;
    }

    //For WAR deployment
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DrivingSchoolApplication.class);
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource getMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(String.format("file:%s/config/messages/app", System.getenv("DRIVINGSCHOOL_ROOT")));
        messageSource.setDefaultEncoding(DEF_ENCODING);
        messageSource.setCacheSeconds(5);
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor getMessageSourceAccessor(ReloadableResourceBundleMessageSource messageSource) {
        MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
        return messageSourceAccessor;
    }

}
