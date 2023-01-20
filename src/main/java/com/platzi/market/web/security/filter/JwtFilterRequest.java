package com.platzi.market.web.security.filter;

import com.platzi.market.domain.service.MarketUserDetailsService;
import com.platzi.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilterRequest extends OncePerRequestFilter {

    @Autowired
    @Deprecated
    private JWTUtil jwtUtil;

    @Autowired
    private MarketUserDetailsService marketUserDetailsService;

    /**
     * If the request has an Authorization header and it starts with the word Bearer, then extract the username from the
     * token, validate the token, and set the authentication in the context
     *
     * @param request The request object.
     * @param response The response object.
     * @param filterChain The filter chain that is used to invoke the next filter in the chain.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Getting the Authorization header from the request.
        String authorizationHeader = request.getHeader("Authorization");

        // Checking if the request has the Authorization header and if it starts with the word Bearer.
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String jwt = authorizationHeader.substring(7);
            String username = jwtUtil.extractUsername(jwt);

            // Checking if the user is already authenticated.
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){
                UserDetails userDetails = marketUserDetailsService.loadUserByUsername(username);

                // Validating the token and setting the authentication in the context.
                if(jwtUtil.validateToken(jwt, userDetails)){
                    UsernamePasswordAuthenticationToken authToke = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToke.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToke);
                }
            }
        }

        // Invoking the next filter in the chain.
        filterChain.doFilter(request, response);
    }
}
