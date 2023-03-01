package com.eliel.inotes.config;

import com.eliel.inotes.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component @RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader("Authorization");
        if(NonHeaders(authorization)){
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = authorization.substring(7);
        final String email = jwtService.extractUsername(jwt);
        if(email != null && !isAuthenticated()){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            if(!jwtService.isTokenExpired(jwt)){
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }

    public boolean NonHeaders(String authorization){
        return authorization == null || !authorization.startsWith("Bearer ");
    }

    public boolean isAuthenticated(){
        return SecurityContextHolder.getContext().getAuthentication() != null;
    }
}
