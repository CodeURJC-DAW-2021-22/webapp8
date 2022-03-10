package webapp8.webandtech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import webapp8.webandtech.service.RepositoryUserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    

    	
	@Autowired
	public RepositoryUserDetailsService userDetailServices;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailServices).passwordEncoder(passwordEncoder());
	 }
	
     @Override
     protected void configure(HttpSecurity http) throws Exception {
         
          http.authorizeRequests().antMatchers( "/css/**").permitAll();
          http.authorizeRequests().antMatchers( "/js/**").permitAll();
          http.authorizeRequests().antMatchers( "/lib/**").permitAll();
          http.authorizeRequests().antMatchers( "/js/**").permitAll();
          http.authorizeRequests().antMatchers( "/images/**").permitAll();
          http.authorizeRequests().antMatchers( "/fonts/**").permitAll();
          http.authorizeRequests().antMatchers( "/ajax/**").permitAll();
          http.authorizeRequests().antMatchers("/").permitAll();
          http.authorizeRequests().antMatchers("/index").permitAll();
          http.authorizeRequests().antMatchers("/login").permitAll();
          http.authorizeRequests().antMatchers("/registerUser").permitAll();
          http.authorizeRequests().antMatchers("/componentes").permitAll();
          http.authorizeRequests().antMatchers("/perifericos").permitAll();
          http.authorizeRequests().antMatchers("/telefonos").permitAll();
          http.authorizeRequests().antMatchers("/productImg1/**").permitAll();
          http.authorizeRequests().antMatchers("/productImg2").permitAll();
          http.authorizeRequests().antMatchers("/productImg3").permitAll();
          http.authorizeRequests().antMatchers("/products/**").permitAll();
          http.authorizeRequests().antMatchers("/forgotPassword").permitAll();
          http.authorizeRequests().antMatchers("/error").permitAll();
          http.authorizeRequests().antMatchers("httpss://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js").permitAll();
          http.authorizeRequests().antMatchers("/api/**").hasAnyRole("USER");
          http.authorizeRequests().antMatchers("/admin").hasAnyRole("ADMIN");
 
          
          http.authorizeRequests().anyRequest().authenticated();
 
          http.formLogin().loginPage("/login");
          http.formLogin().usernameParameter("username");
          http.formLogin().passwordParameter("password");
          http.formLogin().defaultSuccessUrl("/index",true);
          http.formLogin().failureUrl("/login");
          
 
          http.logout().logoutUrl("/logout");
          http.logout().logoutSuccessUrl("/");		 
          // Disable CSRF at the moment
     }
 
 }
 