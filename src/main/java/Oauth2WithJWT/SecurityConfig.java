package Oauth2WithJWT;

import org.eclipse.jetty.server.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
	
	
	@Autowired
	Authentication successHandler;
	
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
	
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {    
        http.csrf().disable().cors().disable()
        .authorizeRequests()
        .antMatchers("/login/**").permitAll()
        .antMatchers("/dashboard").permitAll()
        .anyRequest().authenticated().and()
        .formLogin().loginPage("/login").permitAll()
        .and()
       .oauth2Login().loginPage("/login").successHandler(successHandler);
        //http.addFilterBefore(authenticationTokenFilterBean(), GenericFilterBean.class);
        return http.build();

    }
	/*
	 * @Bean public IsSecureFilter authenticationTokenFilterBean() throws Exception
	 * { return new IsSecureFilter(); }
	 */

}
