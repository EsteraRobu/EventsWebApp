package com.main.erobu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String CLIENT_ROLE = "CLIENT";
    public static final String EDITOR_ROLE = "EDITOR";

    @Value("${spring.queries.clients-query}")
    private String clientsQuery;

    @Value("${spring.queries.editors-query}")
    private String editorsQuery;

    @Value("${spring.queries.admins-query}")
    private String adminsQuery;

    @Value("${spring.queries.clients-roles-query}")
    private String clientsRolesQuery;

    @Value("${spring.queries.editors-roles-query}")
    private String editorsRolesQuery;

    @Value("${spring.queries.admins-roles-query}")
    private String adminsRolesQuery;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/index").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/dist/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/admin/**").hasAuthority(ADMIN_ROLE)
                //.antMatchers("/publisher/**").hasAuthority(PUBLISHER_ROLE)
                .antMatchers("/client/**").hasAuthority(CLIENT_ROLE)
                .antMatchers("/order/**").hasAuthority(CLIENT_ROLE)
                .antMatchers("/guest/**").permitAll()
                .antMatchers("/profile/**").permitAll()
                .antMatchers("/event/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/index")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout()
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(clientsQuery)
                .authoritiesByUsernameQuery(clientsRolesQuery);
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(editorsQuery)
                .authoritiesByUsernameQuery(editorsRolesQuery);
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(adminsQuery)
                .authoritiesByUsernameQuery(adminsRolesQuery);


    }
}
