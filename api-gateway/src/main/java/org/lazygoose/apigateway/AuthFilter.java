/**
 * 
 */
package org.lazygoose.apigateway;

import java.nio.charset.StandardCharsets;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Deepak N
 *
 */

@Component(AuthFilter.IDENTIFIER)
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config>  {

	public static final String IDENTIFIER = "auth-filter";
	
	@Getter
	@Setter
	public static class Config {
		private String baseMessage;
	    private boolean preLogger;
	    private boolean postLogger;
    }
	
	public AuthFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			HttpHeaders headers = request.getHeaders();
			if (!headers.containsKey(HttpHeaders.AUTHORIZATION)) {
				 return this.onError(exchange, "No Authorization header", HttpStatus.UNAUTHORIZED);
			}
			
			String authorizationHeader = headers.get(HttpHeaders.AUTHORIZATION).get(0);
			  
            if (!this.isAuthorizationValid(authorizationHeader)) {
                return this.onError(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
            }

            ServerHttpRequest modifiedRequest = request.mutate().
                    header("secret", RandomStringUtils.random(10)).
                    build();

            return chain.filter(exchange.mutate().request(modifiedRequest).build());
        };
	}
	
	private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus)  {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        byte[] bytes = err.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Flux.just(buffer));
    }
	
	private boolean isAuthorizationValid(String authorizationHeader) {
        boolean isValid = true;

        // Logic for checking the value

        return isValid;
    }

}
