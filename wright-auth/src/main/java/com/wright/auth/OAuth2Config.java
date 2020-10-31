package com.wright.auth;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

	@Autowired
	public PasswordEncoder passwordEncoder;

	@Autowired
	public UserDetailsService kiteUserDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenStore jwtTokenStore;

	@Autowired
	private JwtAccessTokenConverter jwtAccessTokenConverter;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private TokenEnhancer jwtTokenEnhancer;


	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		/**
		 * redis token 方式
		 */
//	        endpoints.authenticationManager(authenticationManager)
//	                .userDetailsService(kiteUserDetailsService)
//	                .tokenStore(jwtTokenStore)
//	                .accessTokenConverter(jwtAccessTokenConverter);
		/**
		 * jwt 增强模式
		 */
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		List<TokenEnhancer> enhancerList = new ArrayList<>();
		enhancerList.add(jwtTokenEnhancer);
		enhancerList.add(jwtAccessTokenConverter);
		enhancerChain.setTokenEnhancers(enhancerList);

		endpoints.tokenStore(jwtTokenStore).userDetailsService(kiteUserDetailsService)
				/**
				 * 支持 password 模式
				 */
				.authenticationManager(authenticationManager).tokenEnhancer(enhancerChain)
				.accessTokenConverter(jwtAccessTokenConverter);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.jdbc(dataSource);
//	        clients.inMemory()
//	                .withClient("order-client")
//	                .secret(passwordEncoder.encode("order-secret-8888"))
//	                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//	                .accessTokenValiditySeconds(3600)
//	                .scopes("all")
//	                .and()
//	                .withClient("user-client")
//	                .secret(passwordEncoder.encode("user-secret-8888"))
//	                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
//	                .accessTokenValiditySeconds(3600)
//	                .scopes("all")
//	                .and()
//	                .withClient("code-client")
//	                .secret(passwordEncoder.encode("code-secret-8888"))
//	                .authorizedGrantTypes("authorization_code","refresh_token", "http://localhost:6102/client-authcode/login")
//	                .accessTokenValiditySeconds(3600)
//	                .scopes("all");
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.allowFormAuthenticationForClients();
		security.checkTokenAccess("isAuthenticated()");
		security.tokenKeyAccess("isAuthenticated()");
	}

}
