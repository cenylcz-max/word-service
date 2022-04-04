package com.cenylcz.listener;

import com.cenylcz.event.word.WordCreateEvent;
import com.cenylcz.service.WordService;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Configuration
@KafkaListener(id = "word-consumer", topics = "word", autoStartup = "true")
public class KafkaWordEventListener {

    private WordService wordService;

    public KafkaWordEventListener(WordService wordService) {
        this.wordService = wordService;
    }

    @KafkaHandler
    public void listen(@Payload WordCreateEvent event) {
        this.wordService.create(event.getData());
    }

    @KafkaHandler(isDefault = true)
    public void listenDefault(Object message) {
        System.out.println(message);
    }
}
