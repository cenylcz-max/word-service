package com.cenylcz.service;

import com.cenylcz.domain.dictionary.Word;
import com.cenylcz.jpa.repository.WordRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("wordService")
public class WordService {

    private WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> retrieveAllWords() {
        return this.wordRepository.findAll();
    }

    public Word create(Word word) {
        return this.wordRepository.save(word);
    }
}
