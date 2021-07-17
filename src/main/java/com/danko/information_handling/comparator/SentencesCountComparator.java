package com.danko.information_handling.comparator;

import com.danko.information_handling.entity.InformationComponent;

import java.util.Comparator;

public class SentencesCountComparator implements Comparator<InformationComponent> {
    @Override
    public int compare(InformationComponent o1, InformationComponent o2) {
        int countSentencesFirstParagraph = o1.getChildren().size();
        int countSentencesSecondParagraph = o2.getChildren().size();
        return Integer.compare(countSentencesFirstParagraph, countSentencesSecondParagraph);
    }
}
