package com.cenylcz.router;

import com.cenylcz.handler.WordHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {

    @Bean
    public RouterFunction<ServerResponse> Router(WordHandler wordHandler) {
        return RouterFunctions.nest(path("/word-service"), wordRouter(wordHandler));
    }

    private RouterFunction<ServerResponse> wordRouter(WordHandler wordHandler) {
        return RouterFunctions.route(GET("/words").and(accept(MediaType.APPLICATION_JSON)), wordHandler::retrieveAllWords)
                .andRoute(POST("/words").and(contentType(MediaType.APPLICATION_JSON)), wordHandler::create)
                .andRoute(PUT("/words").and(contentType(MediaType.APPLICATION_JSON)), wordHandler::update);
    }
}

