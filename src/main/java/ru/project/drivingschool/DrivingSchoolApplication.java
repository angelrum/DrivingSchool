package ru.project.drivingschool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.project.drivingschool.**.repository"})
public class DrivingSchoolApplication extends SpringBootServletInitializer {
    private static final String DEF_ENCODING = "UTF-8";

    public static void main(String[] args) {
        SpringApplication.run(DrivingSchoolApplication.class, args);
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter jackson2HttpMessageConverter() {
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(JacksonObjectMapper.getMapper());
//        return converter;
//    }
//
//    @Bean
//    public StringHttpMessageConverter stringHttpMessageConverter() {
//        StringHttpMessageConverter messageConverter = new StringHttpMessageConverter();
//        messageConverter.setSupportedMediaTypes(List.of(
//                new MediaType(MediaType.TEXT_PLAIN, Charset.forName(DEF_ENCODING)),
//                new MediaType(MediaType.TEXT_HTML, Charset.forName(DEF_ENCODING))));
//        return messageConverter;
//    }

    //For WAR deployment
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DrivingSchoolApplication.class);
    }

}
