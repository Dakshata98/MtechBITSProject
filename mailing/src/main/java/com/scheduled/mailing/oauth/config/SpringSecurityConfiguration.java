package com.scheduled.mailing.oauth.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.scheduled.mailing.dao.ScheduledMailDaoImpl;
import com.scheduled.mailing.dto.User;

@Configuration
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private ScheduledMailDaoImpl em;

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////		http.authorizeRequests()
////			.antMatchers("/api/scheduling/**").permitAll()
////        	.anyRequest().authenticated().and()
////        	.csrf()
////            .ignoringAntMatchers("/api/scheduling/**") // Ignore CSRF protection for your scheduling API
////			.and().oauth2Login();
//		http.authorizeRequests()
//		.antMatchers("/", "/api/scheduling/**").permitAll()
////		.antMatchers(HttpMethod.POST, "/api/scheduling/**").permitAll()
////		.antMatchers(HttpMethod.PUT, "/api/scheduling/**").permitAll()
////		.antMatchers(HttpMethod.DELETE, "/api/scheduling/**").permitAll()// Specify the base path of your controller
//    	.anyRequest().authenticated().and()
//    	.csrf()
//        .ignoringAntMatchers("/api/scheduling/**") // Ignore CSRF protection for your scheduling API
//		.and().oauth2Login().successHandler(this.customAuthenticationSuccessHandler());
//		return http.build();
//	}

	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/", "/api/scheduling/**").permitAll()
////		.antMatchers(HttpMethod.POST, "/api/scheduling/**").permitAll()
////		.antMatchers(HttpMethod.PUT, "/api/scheduling/**").permitAll()
////		.antMatchers(HttpMethod.DELETE, "/api/scheduling/**").permitAll()// Specify the base path of your controller
//				.anyRequest().authenticated().and().csrf().ignoringAntMatchers("/api/scheduling/**") // Ignore CSRF
//																										// protection
//																										// for your
//																										// scheduling
//																										// API
//				.and().oauth2Login().successHandler(this.customAuthenticationSuccessHandler());
////		return http.build();
		
		http.cors().and().authorizeRequests().antMatchers("/", "/api/scheduling/**").permitAll()
//		.antMatchers(HttpMethod.POST, "/api/scheduling/**").permitAll()
//		.antMatchers(HttpMethod.PUT, "/api/scheduling/**").permitAll()
//		.antMatchers(HttpMethod.DELETE, "/api/scheduling/**").permitAll()// Specify the base path of your controller
				.anyRequest().authenticated().and().csrf().ignoringAntMatchers("/api/scheduling/**") // Ignore CSRF
																										// protection
																										// for your
																										// scheduling
																										// API
				.and().oauth2Login().successHandler(this.customAuthenticationSuccessHandler());
//		return http.build();
	}
	
	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:3000"); // Add the origin of your React app
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

	private AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
		return (request, response, authentication) -> {
			OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
			String email = oauth2User.getAttribute("email");
			String name = oauth2User.getAttribute("name");
			List<User> existingUser = em.findByEmailID(email);
			if (existingUser==null || (existingUser.isEmpty())) {
				User user = new User();
				user.setId(new Integer(em.nextUserID().toString()));
				user.setEmail(email);
				user.setName(name);
				em.saveUserData(user);
			}

//            Optional<User> existingUser = em.findByEmailID(email);
//            if (!existingUser.isPresent()) {
//                User newUser = new User(email, name);
//                userRepository.save(newUser);
//            }

			// You can redirect the user to a specific URL after successful login
			response.sendRedirect("/api/scheduling/fetchUserMailData?mailId="+email);
		};
	}

}
