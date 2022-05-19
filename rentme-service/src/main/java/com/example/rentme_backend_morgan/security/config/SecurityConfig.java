package com.example.rentme_backend_morgan.security.config;


import com.example.rentme_backend_morgan.business.api.BusinessApiEndPoints;
import com.example.rentme_backend_morgan.jwt.JWTAuthenticationEntryPoint;
import com.example.rentme_backend_morgan.jwt.JWTFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.rentme_backend_morgan.security.api.SecurityApi;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JWTFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();//because if we exends from WebSecurityConfigurerAdapter this include by default
        http.httpBasic();//because if we exends from WebSecurityConfigurerAdapter this include by default

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.cors();

        //http.authorizeRequests().antMatchers("/**").permitAll(); // init backdoor

        // only ADMIN, ho can manage objects and accounts
        http.authorizeRequests()
                .antMatchers(
                        SecurityApi.SECURITY + SecurityApi.ADD_ACCOUNT,
                        SecurityApi.SECURITY + SecurityApi.GRANT_ROLE,
                        SecurityApi.SECURITY + SecurityApi.DEPRIVE_ROLE,
//                        SecurityApi.SECURITY + SecurityApi.GET_ALL_ACCOUNTS,
//                        SecurityApi.SECURITY + SecurityApi.REMOVE_ACCOUNT,
                        SecurityApi.SECURITY + SecurityApi.REMOVE_USER,
//                        SecurityApi.JWT + SecurityApi.AUTHENTICATE,
                        BusinessApiEndPoints.APP + BusinessApiEndPoints.ADMIN)
                .hasRole("ADMIN")

                // only MANAGER, ho can only manage objects, not users
                .antMatchers(
                        BusinessApiEndPoints.APP + BusinessApiEndPoints.MANAGER)
                .hasRole("MANAGER")

                // only USER(Renter)
                .antMatchers(
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_GET_PROFILE,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_EDIT_PROFILE,

                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_FIND_PLACE,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_GET_FULL_DATA_BY_PLACE,

                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_ADD_FAVORITES,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_GET_FAVORITES,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_REMOVE_FAVORITES,

                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_ADD_HISTORY,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_GET_HISTORY,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_REMOVE_HISTORY,

                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_REQUEST_TOUR,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_APPLY_OWNER,
                        BusinessApiEndPoints.USER + BusinessApiEndPoints.USER_MESSAGE_TO_OWNER
                ).hasRole("USER")

                // only OWNER
                .antMatchers(
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_ADD_RENT_OBJECT,
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_ADD_ANNOUNCEMENT,
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_GET_PROFILE,
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_EDIT_PROFILE,
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_EDIT_OBJECT,
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_GET_OBJECTS,
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_REMOVE_OBJECT,
                        BusinessApiEndPoints.OWNER + BusinessApiEndPoints.OWNER_SEND_MESSAGE
                )
                .hasAnyRole("OWNER")

                // only GUEST
                .antMatchers(
                        SecurityApi.SECURITY + SecurityApi.GET_ALL_ACCOUNTS,
                        SecurityApi.SECURITY + SecurityApi.REMOVE_ACCOUNT,

                        SecurityApi.JWT + SecurityApi.REGISTER,
                        SecurityApi.SECURITY + SecurityApi.ADD_USER,
                        SecurityApi.SECURITY + SecurityApi.ADD_OWNER)
                .permitAll();

        // close everything else for not authenticated
        http.authorizeRequests().anyRequest().authenticated();

    }


}

/*
//
ADMIN
MANAGER

OWNER
USER
GUEST

 */


