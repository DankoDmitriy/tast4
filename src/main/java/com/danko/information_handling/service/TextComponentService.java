package com.danko.information_handling.service;

import com.danko.information_handling.entity.TextComponentInformation;
import com.danko.information_handling.exception.TextException;

import java.util.List;

public interface TextComponentService {
    long countVowels(TextComponentInformation component) throws TextException;

    long countConsonants(TextComponentInformation component) throws TextException;

    List<TextComponentInformation> findSentencesOfMaxWord(TextComponentInformation component) throws TextException;

    void removeSentencesWithMinWords(TextComponentInformation component, int minWords) throws TextException;

    void sortParagraphsBySentences(TextComponentInformation component) throws TextException;

    int countEqualWords(TextComponentInformation component, String wordToSearchFor) throws TextException;
}
