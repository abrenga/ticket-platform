package it.tickets.manager.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean /**comunicano a spring quale meccanismo di autenticazione vogliono implementare*/
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/notes/{id}/addNote","/tickets/afterLogin","/searchTickets","/tickets/{id}", "tickets/updateTicket/{id}").hasAnyAuthority("USER", "ADMIN")
                .requestMatchers(
                        "/tickets/createTicket",
                        "/tickets/delete/{id}",
                        "/tickets")
                    .hasAuthority("ADMIN")


                        .requestMatchers("user/{userId}",
                                "/notes/{id}",
                                "/tickets/{id}")
                        .hasAuthority("USER")



                        .anyRequest().permitAll())
                .csrf(c -> c.disable())
                .formLogin(l->l.defaultSuccessUrl("/tickets/afterLogin",true));

        return http.build();
    }


    @Bean
    DatabaseUserDetailService userDetailsService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = authenticationProvider();

        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }
}
