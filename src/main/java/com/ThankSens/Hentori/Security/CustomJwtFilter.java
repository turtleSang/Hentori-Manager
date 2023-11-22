package com.ThankSens.Hentori.Security;

import com.ThankSens.Hentori.Entity.AdminEntity;
import com.ThankSens.Hentori.Security.Units.JjwtHelper;
import com.google.gson.Gson;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class CustomJwtFilter extends OncePerRequestFilter {
    private JjwtHelper jjwtHelper;
    private Gson gson = new Gson();

    @Autowired
    public CustomJwtFilter(JjwtHelper jjwtHelper) {
        this.jjwtHelper = jjwtHelper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("token");
        String json = jjwtHelper.authenticateJwt(token);
        if (json != null){
            AdminEntity adminEntity = gson.fromJson(json, AdminEntity.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(adminEntity.getUsername(),"",new ArrayList<>());
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);


    }


}
