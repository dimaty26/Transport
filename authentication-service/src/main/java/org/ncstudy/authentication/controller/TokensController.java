package org.ncstudy.authentication.controller;

import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokensController {

    private final TokenStore tokenStore;

    public TokensController(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    // Logout alternative for stateless ?end?
    @DeleteMapping("/token/revoke")
    public void revokeTokens(@RequestParam(required = false) String access_token, @RequestParam(required = false) String refresh_token) {
        JdbcTokenStore store = (JdbcTokenStore) tokenStore;
        if (!StringUtils.isEmpty(access_token))
            store.removeAccessToken(access_token);
        if (!StringUtils.isEmpty(refresh_token)) {
            store.removeAccessTokenUsingRefreshToken(refresh_token);
            store.removeRefreshToken(refresh_token);
        }
    }
}
