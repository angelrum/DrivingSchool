package ru.project.drivingschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;
import ru.project.drivingschool.service.UserService;

import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.*;

// Пример реализации с минимальным привлечением Spring Security
// https://octoperf.com/blog/2018/03/08/securing-rest-api-spring-security/#securityconfig

@Configuration
@EnableWebSecurity
public class DrivingSchoolSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService service;

    @Value("${spring.profiles.active:}")
    private String activeProfiles;

    @Value("${cors.allowed.origins}")
    private String corsAllowedOrigins;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                    .csrf().disable()
                    .authorizeRequests()
                    .anyRequest().fullyAuthenticated()
                .and()
                    .httpBasic()
                    .authenticationEntryPoint((request, response, e) -> resolver.resolveException(request, response, null, e))//обрабатываем ошибку аутентификации AuthenticationException
                .and()
                    .logout(logout -> logout
                            .logoutUrl("/auth/logout")
                            .clearAuthentication(true)
                            .invalidateHttpSession(true)
                            .logoutSuccessHandler((req, resp, auth) -> resp.setStatus(HttpServletResponse.SC_OK)) //отключаем редирект на /login?logout https://www.baeldung.com/spring-security-disable-logout-redirects
                    );
    }

    //https://stackoverflow.com/questions/40418441/spring-security-cors-filter
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin(corsAllowedOrigins);
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_ORIGIN, ACCESS_CONTROL_REQUEST_METHOD,
                ACCESS_CONTROL_REQUEST_HEADERS, ORIGIN, CACHE_CONTROL, CONTENT_TYPE, AUTHORIZATION, "x-csrf-token"));
        configuration.setAllowedMethods(Arrays.stream(HttpMethod.values()).map(Enum::name).collect(Collectors.toList()));//List.of("POST", "GET", "OPTIONS", "DELETE", "PUT")
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.service);
    }

//https://www.baeldung.com/spring-security-session#concurrent-sessions
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    @Profile("dev")
    public PasswordEncoder devPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Profile("prod")
    public PasswordEncoder prodPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
