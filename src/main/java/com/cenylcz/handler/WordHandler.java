package com.cenylcz.handler;

import com.cenylcz.domain.dictionary.Word;
import com.cenylcz.service.WordService;
import org.springframework.http.MediaType;
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

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Word.class)
                .map(w -> wordService.create(w))
                .flatMap(word -> ServerResponse.created(null).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(word)))
                .onErrorResume(e -> Mono.error(e));
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(Word.class)
                .map(w -> wordService.create(w))
                .flatMap(word -> ServerResponse.created(null).contentType(MediaType.APPLICATION_JSON).body(BodyInserters.fromValue(word)))
                .onErrorResume(e -> Mono.error(e));
    }
}
