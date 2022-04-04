package com.cenylcz.handler;

import com.cenylcz.service.WordService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class WordHandler {

    private WordService wordService;

    public WordHandler(WordService wordService) {
        this.wordService = wordService;
    }

    public Mono<ServerResponse> retrieveAllWords(ServerRequest serverRequest) {
        return ServerResponse.ok().body(BodyInserters.fromValue(this.wordService.retrieveAllWords()));
    }
}
