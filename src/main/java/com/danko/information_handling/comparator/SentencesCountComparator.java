package com.danko.information_handling.comparator;

import com.danko.information_handling.entity.TextComponentInformation;

import java.util.Comparator;

public class SentencesCountComparator implements Comparator<TextComponentInformation> {
    @Override
    public int compare(TextComponentInformation o1, TextComponentInformation o2) {
        int countSentencesFirstParagraph = o1.getChildren().size();
        int countSentencesSecondParagraph = o2.getChildren().size();
        return Integer.compare(countSentencesFirstParagraph, countSentencesSecondParagraph);
    }
}
