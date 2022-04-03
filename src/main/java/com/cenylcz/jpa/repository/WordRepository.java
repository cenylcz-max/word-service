package com.cenylcz.jpa.repository;

import com.cenylcz.domain.dictionary.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Integer> {
}
