package com.danko.information_handling.service;

import com.danko.information_handling.entity.InformationComponent;
import com.danko.information_handling.exception.TextException;

import java.util.List;

public interface TextComponentService {
    long countVowels(InformationComponent component) throws TextException;

    long countConsonants(InformationComponent component) throws TextException;

    List<InformationComponent> findSentencesOfMaxWord(InformationComponent component) throws TextException;

    void removeSentencesWithMinWords(InformationComponent component, int minWords) throws TextException;

    void sortParagraphsBySentences(InformationComponent component) throws TextException;

    int countEqualWords(InformationComponent component, String wordToSearchFor) throws TextException;
}
