package com.spring.webservice.security;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (!hasToken(containerRequestContext)) {
            var response = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Unauthorized"))
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
            containerRequestContext.abortWith(response);
        }
    }

    private boolean hasToken(ContainerRequestContext requestContext) {
        // Extract Authorization header details
        var authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return false;
        }
        // Extract the token
        var token = authorizationHeader.substring(TOKEN_PREFIX.length()).trim();
        return token != null && token.length() > 0;
    }

    class ErrorResponse {
        String error;

        ErrorResponse(String error) { this.error = error; }

        public String getError() { return error; }
    }
}
