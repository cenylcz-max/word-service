package com.cenylcz.service;

import com.cenylcz.constant.Constant;
import com.cenylcz.domain.dictionary.Word;
import com.cenylcz.jpa.repository.WordRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        Optional<Word> existWord = this.wordRepository.findById(word.getWord());
        String alphabet = String.valueOf(word.getWord().charAt(0));
        word.setAlphabet(Constant.alphabetMap.get(alphabet.toUpperCase()));
        if (existWord.isPresent()) {
            word.setFrequency(existWord.get().getFrequency() + 1);
        }
        return this.wordRepository.save(word);
    }
}
