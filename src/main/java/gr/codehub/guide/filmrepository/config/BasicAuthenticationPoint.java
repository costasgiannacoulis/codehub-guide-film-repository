package gr.codehub.guide.filmrepository.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * Used by the <code>ExceptionTranslationFilter</code> to commence authentication via the
 * {@link BasicAuthenticationFilter}.
 * <p>
 * Once a user agent is authenticated using BASIC authentication, logout requires that the browser be closed or an
 * unauthorized (401) header be sent. The simplest way of achieving the latter is to call the
 * {@link #commence(HttpServletRequest, HttpServletResponse, AuthenticationException)} method below. This will
 * indicate to the browser its credentials are no longer authorized, causing it to prompt the user to login again.
 * </p>
 */
@Component
public class BasicAuthenticationPoint extends BasicAuthenticationEntryPoint {
	/**
	 * Invoked by a BeanFactory after it has set all bean properties supplied
	 * (and satisfied BeanFactoryAware and ApplicationContextAware).
	 * <p>
	 * This method allows the bean instance to perform initialization only
	 * possible when all bean properties have been set and to throw an
	 * exception in the event of misconfiguration.
	 * </p>
	 *
	 * @throws Exception in the event of misconfiguration (such
	 *                   as failure to set an essential property) or if initialization fails.
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("Film-Realm");
		super.afterPropertiesSet();
	}

	/**
	 * Method demonstrating the flow in case of a client who has not provided credentials when accessing a protected
	 * resource.
	 *
	 * @param request  The http request.
	 * @param response The http response.
	 * @param authEx   The authentication exception
	 *
	 * @throws IOException if response cannot not be written in response's output stream.
	 */
	@Override
	public void commence(
		final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authEx)
		throws IOException {
		response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		final PrintWriter writer = response.getWriter();
		writer.println("HTTP Status 401 - " + authEx.getMessage());
	}
}
