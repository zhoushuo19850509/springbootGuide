package com.example.gatewayservice;

import com.example.gatewayservice.configuration.UriConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@SpringBootApplication
@EnableConfigurationProperties(UriConfiguration.class)
public class GatewayServiceApplication {

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder, UriConfiguration uriConfiguration){
		String httpUri = uriConfiguration.getHttpbin();
		return builder.routes()
				.route (p -> p
						.path("/get")
						.filters(f -> f.addRequestHeader("Hello","World"))
						.uri(httpUri))
				.route(p -> p
						.host("*.hystrix.com")
						.filters(f -> f.hystrix(
								config -> config
										.setName("mycmd")
										.setFallbackUri("forward:/fallback")))
						.uri(httpUri))
				.build();

	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

}
