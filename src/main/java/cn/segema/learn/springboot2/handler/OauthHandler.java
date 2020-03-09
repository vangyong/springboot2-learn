package cn.segema.learn.springboot2.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.request.AuthGithubRequest;
import me.zhyd.oauth.request.AuthRequest;
import reactor.core.publisher.Mono;

@Service
public class OauthHandler {

	public Mono<ServerResponse> renderAuth(ServerRequest request) {
		AuthRequest authRequest = getAuthRequest();
		// response.sendRedirect(authRequest.authorize());
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(null));
	}

	public Mono<ServerResponse> login(ServerRequest request) {
		AuthRequest authRequest = getAuthRequest();
		String code = request.pathVariable("code");
		// AuthResponse authResponse = authRequest.login(code);

		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromObject(null));
	}

	private AuthRequest getAuthRequest() {
		return new AuthGithubRequest(AuthConfig.builder().clientId("Client ID").clientSecret("Client Secret")
				.redirectUri("Authorization callback URL").build());
	}

}
