/*package it.tickets.manager.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {
    @Bean /**comunicano a spring quale meccanismo di autenticazione vogliono implementare
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/tickets/createTicket","/tickets/delete/**","/tickets","tickets/updateTicket/{id}").hasAuthority("ADMIN")
                .requestMatchers("/notes//{id}").hasAuthority("User")
                                .requestMatchers("/notes/{id}/addNote","/notes//{id}", "/tickets/{id}").hasAuthority("USER,ADMIN")


                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }


    @Bean
    DatabaseUserDetailService databaseUserDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(databaseUserDetailService());
        return daoAuthenticationProvider;
    }


}*/
