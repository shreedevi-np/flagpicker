/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apple.flagpicker.filters;

import com.apple.flagpicker.config.FlagPickerProperties;
import com.apple.flagpicker.exceptions.UnauthorizedTokenException;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author Shree
 */
public class HeaderTokenAuthFilter extends OncePerRequestFilter {

    private static final String UNAUTHORIZED_ERROR = "UNAUTHORIZED";

    private final AuthenticationProvider authenticationProvider;
    private final FlagPickerProperties properites;

    public HeaderTokenAuthFilter(AuthenticationProvider authenticationProvider,
            FlagPickerProperties properites) {
        super();
        this.authenticationProvider = authenticationProvider;
        this.properites = properites;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            final String token = request.getHeader("auth-token");

            if (token == null || token.isEmpty() || !token.equals(properites.getValue("fp.security.token", ""))) {
                throw new UnauthorizedTokenException();
            }

            final Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken("token", token));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (UnauthorizedTokenException ex) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, UNAUTHORIZED_ERROR);
        }
    }

}
