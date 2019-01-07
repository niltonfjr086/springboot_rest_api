package com.apirest.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.apirest.swagger.SwaggerConfig;

@Component
public class MyBasicAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("Works");
		super.afterPropertiesSet();
	}

	private String rescueLastArgument(HttpServletRequest request) {
		String[] reqArray = request.getRequestURL().toString().split("/");
		String dispatcherParam = reqArray[reqArray.length - 1];

		return dispatcherParam;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
			throws IOException, ServletException {

		String value = request.getHeader("WWW-Authenticate");
		if (value != null && value.equals("Basic realm=" + getRealmName().toString())) {

			String dispatcherParam = rescueLastArgument(request);
			response.setStatus(HttpServletResponse.SC_OK);
			request.getRequestDispatcher(dispatcherParam).forward(request, response);

		} else {

			if (SwaggerConfig.isSwaggerResource(request)) {
				response.setStatus(HttpServletResponse.SC_OK);
				request.getRequestDispatcher(rescueLastArgument(request)).forward(request, response);

			} else {

				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				PrintWriter writer = response.getWriter();
				writer.println("HTTP Status 401 - " + authEx.getMessage());
			}

		}

	}

}