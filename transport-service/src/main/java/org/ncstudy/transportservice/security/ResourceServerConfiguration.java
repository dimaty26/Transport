package org.ncstudy.transportservice.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "api/v1/transport/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "api/v1/transport/**").authenticated()
                .antMatchers(HttpMethod.PUT, "api/v1/transport/**").authenticated()
                .antMatchers("test/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "api/v1/card/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "api/v1/card/**").authenticated()
                .antMatchers(HttpMethod.PUT, "api/v1/card/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "api/v1/bus_stop/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "api/v1/bus_stop/**").authenticated()
                .antMatchers(HttpMethod.PUT, "api/v1/bus_stop/**").authenticated()
                .anyRequest().permitAll();
    }
}
