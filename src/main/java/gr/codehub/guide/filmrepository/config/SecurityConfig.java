package gr.codehub.guide.filmrepository.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Security configuration.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private BasicAuthenticationPoint basicAuthenticationPoint;
	/**
	 * If we are putting together a demo or a sample, it is a bit cumbersome to take time to hash the passwords of your
	 * users. There are convenience mechanisms to make this easier, but this is still not intended for production.
	 * <code>
	 *
	 * @Configuration
	 * @EnableWebSecurity public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	 * 	// add our users for in memory authentication
	 * 	auth.inMemoryAuthentication().withUser("user1").password("test123").roles("EMPLOYEE");
	 * 	auth.inMemoryAuthentication().withUser("user2").password("test123").roles("MANAGER");
	 * 	auth.inMemoryAuthentication().withUser("user3").password("test123").roles("ADMIN");
	 * 	}
	 * 	}
	 * 	</code>
	 * 	This does hash the password that is stored, but the passwords are still exposed in memory and in the
	 * 	compiled source code. Therefore, it is still not considered secure for a production environment.
	 * 	For production, you should hash your passwords externally.
	 * 	<p>
	 * 	When we use Spring Security 5, we need to explicitly provide the PasswordEncoder that our passwords are encoded
	 * 	with. Its not the requirement for the Spring Security 4.2.3. So, to migrate our code from Spring Security 4.2.x
	 * 	we can revert to the previous behavior by exposing a NoOpPasswordEncoder bean.
	 * 	By providing the method mentioned below, we can run our application.
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("acmeuser").password("pass1234").roles("USER");
	}

	/**
	 * Demonstanting basic security setup by allowing only GET requests to be served without providing credentials.
	 *
	 * @param http A {@link HttpSecurity} is similar to Spring Security's XML &lt;http&gt; element in the
	 *             namespace configuration. It allows configuring web based security for specific http
	 *             requests.
	 *
	 * @throws Exception if the configuration is not valid.
	 */
	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/h2/**").permitAll()
			.antMatchers(HttpMethod.GET).permitAll()
			.antMatchers(HttpMethod.PUT).authenticated()
			.antMatchers(HttpMethod.POST).authenticated()
			.antMatchers(HttpMethod.DELETE).authenticated()
			.anyRequest().authenticated();
		http.httpBasic().authenticationEntryPoint(basicAuthenticationPoint);
		http.csrf().disable();
		http.cors().disable();
		// Needed in order to allow H2 console to work properly
		http.headers().frameOptions().disable();
		// See https://www.baeldung.com/spring-security-session for details
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
}
