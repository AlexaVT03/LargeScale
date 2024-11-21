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
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(c -> c.disable())
            .oauth2Login(withDefaults())
            .authorizeHttpRequests(a -> a
                .requestMatchers("/").permitAll() //all requests except viewing the home page require user to be logged in
                .requestMatchers("/user").authenticated()
                .requestMatchers(HttpMethod.GET, "/health-checks/listall").authenticated()
                .anyRequest().authenticated()
            )
            ;

        return http.build();
    }

}