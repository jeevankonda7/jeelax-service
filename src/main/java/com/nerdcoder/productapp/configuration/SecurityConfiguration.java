package com.nerdcoder.productapp.configuration;

import com.nerdcoder.productapp.filters.JwtAuthFilter;
import com.nerdcoder.productapp.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final UserDetailsService userDetailsService;
  private final JwtAuthFilter jwtAuthFilter;

  public SecurityConfiguration(CustomUserDetailsService userDetailsService,
                               JwtAuthFilter jwtAuthFilter) {
    this.userDetailsService = userDetailsService;
    this.jwtAuthFilter = jwtAuthFilter;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpRequest) throws Exception {
     httpRequest
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
//                    auth.requestMatchers("/auth/**", "/swagger-ui/**")
                    auth.anyRequest()
                            .permitAll());
//                            .anyRequest()
//                            .authenticated());
            httpRequest.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            return httpRequest.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,
                                                     PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authenticationProvider =
            new DaoAuthenticationProvider(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(authenticationProvider);

  }


}
