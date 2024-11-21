package lsit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, CustomOidcUserService customOidcUserService) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfo -> userInfo
                    .oidcUserService(customOidcUserService) // Using custom service to determine user roles
                )
            )
            .authorizeHttpRequests(authorize -> authorize
                //authorizations currently non-sensical, just for testing purposes
                .requestMatchers("/").permitAll() //lets anyone access home page
                .requestMatchers(HttpMethod.GET, "/donors/listall").hasAnyRole("Fail_test","DONOR") //hasAnyRole when multiple roles have access
                .requestMatchers(HttpMethod.GET, "/eligibility-forms/listall").hasRole("MEDICAL-STAFF")
                .requestMatchers(HttpMethod.GET, "/blood-samples/listall").hasRole("BLOOD-MANAGEMENT-UNIT")
                .requestMatchers(HttpMethod.GET, "/health-checks/listall").hasRole("FAIL_TEST") //test; should be forbidden
                .anyRequest().authenticated() //all other pages you just need to be logged in; no role requirement
                //TODO: add role restrictions for every call/page
            );

        return http.build();
    }


}