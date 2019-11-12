package org.ncstudy.authentication.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final TokenStore tokenStore;
    private final JwtAccessTokenConverter accessTokenConverter;
    private final JdbcTemplate jdbcTemplate;

    public OAuth2Config(AuthenticationManager authenticationManager, @Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService, TokenStore tokenStore, JwtAccessTokenConverter accessTokenConverter, JdbcTemplate jdbcTemplate) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.tokenStore = tokenStore;
        this.accessTokenConverter = accessTokenConverter;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.jdbc(jdbcTemplate.getDataSource());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore)
                .reuseRefreshTokens(false)
                .accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
        .allowedTokenEndpointRequestMethods()
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
                .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    }
}
