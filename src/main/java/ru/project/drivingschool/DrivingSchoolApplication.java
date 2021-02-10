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

    //For WAR deployment
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DrivingSchoolApplication.class);
    }

}
