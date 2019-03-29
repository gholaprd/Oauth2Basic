package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootApplication

@EnableWebSecurity
public class Oauth21Application extends WebSecurityConfigurerAdapter{

	
	
	private AuthorizacionServerConfiguration auth;
	public static void main(String[] args) {
		SpringApplication.run(Oauth21Application.class, args);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	 @Bean
     @Override
     public UserDetailsService userDetailsService () {
    	
		 User.UserBuilder users = User.withDefaultPasswordEncoder();
	        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	        manager.createUser(users.username("user").password("secret").roles("USER").build());
	        manager.createUser(users.username("admin").password("secret").roles("USER", "ADMIN").build());
	        return manager;
		 
		/*
		 * UserDetails user = User.builder (). username("user") .password
		 * (passwordEncoder (). encode ("secret")) .roles ("USER"). build ();
		 * UserDetails userAdmin = User.builder (). username ("admin") .password
		 * (auth.passwordEncoder().encode("secret")) .roles ("ADMIN"). build (); return
		 * new InMemoryUserDetailsManager (user, userAdmin);
		 */
     }

	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
   	 .csrf (). disable ()
            .authorizeRequests ()
       	 .antMatchers ("/", "/ index", "/ webpublico").permitAll()
       	 .antMatchers ("/ webprivado"). authenticated ()
       	 .antMatchers ("/ webadmin"). hasRole ("ADMIN"). and ()
            .formLogin ()
            .loginPage ("/ login")
            .permitAll ()
            .and ()
            .logout () // Method get then I have disabled CSRF
            .permitAll ();
	}
}
