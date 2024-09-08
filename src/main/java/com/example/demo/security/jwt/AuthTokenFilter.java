package com.example.demo.security.jwt;

import com.example.demo.security.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//Filter all the request made by the user
@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException{

        try {
            String jwt=parseJwt(request);

            //check if the jwt token is not null and is valid
            if((jwt !=null) && (jwtUtils.validateJwtToken(jwt))){

                //get the username from token
                String name=jwtUtils.getUsernameFromJwtToken(jwt);

                //get user details from extracted username
                UserDetails userDetails=userDetailsService.loadUserByUsername(name);

                //Create an authentication object
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                //set details for authentication object as matches to request
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                //Indicate to the security context holder that user is successfully authenticated
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            System.err.println("Cannot set user Auth");
        }

        filterChain.doFilter(request,response);
    }


    //Filter out the token from header
    private String parseJwt(HttpServletRequest request){
        String authHeader=request.getHeader("Authorization");

        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){
            return authHeader.substring(7);
        }

        return null;
    }


}
