package com.asalkar.dto.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.asalkar.oils.services.MyUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	public JWTService jwtService;
	
	@Autowired
	public ApplicationContext context;


    

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, java.io.IOException
    {
//  Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJraWxsIiwiaWF0IjoxNzIzMTgzNzExLCJleHAiOjE3MjMxODM4MTl9.5nf7dRzKRiuGurN2B9dHh_M5xiu73ZzWPr6rbhOTTHs
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;
        try {
        if (authHeader != null && authHeader.startsWith("Bearer ")) 
        {
        	
            token = authHeader.substring(7);
            System.out.println("before extraction username");
            username = jwtService.extractUserName(token);
            System.out.println("after extraction username");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
            System.out.println("userDetails are:"+userDetails.getUsername());
           
            if (jwtService.validateToken(token, userDetails))
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);
                authToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            
            
           
        }
        filterChain.doFilter(request, response);
        }
        catch(ExpiredJwtException e) {
        	
        	System.out.print("exception when token expire:"+e.getMessage());
        	//throw new ServletException(e.getMessage());
        	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            // Build a JSON error response
            String errorResponse = String.format("{\"error\": \"%s\"}", e.getMessage());
            response.getWriter().write(errorResponse);
        }

        
			
		 catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

	            // Build a JSON error response
	            String errorResponse = String.format("{\"error\": \"%s\"}", e.getMessage());
	            response.getWriter().write(errorResponse);

		}
    }
}
