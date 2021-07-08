package com.danko.information_handling.main;

import com.danko.information_handling.entity.TextComponentInformation;
import com.danko.information_handling.parser.DataParser;
import com.danko.information_handling.parser.impl.ParagraphParser;
import com.danko.information_handling.reader.TextReader;
import com.danko.information_handling.service.TextComponentService;
import com.danko.information_handling.service.impl.TextComponentServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static Logger logger = LogManager.getLogger();
    private static String pathToFile = "test_data\\text.txt";

    public static void main(String[] args) throws Exception {
        TextReader reader = new TextReader();
        String text = reader.readText(pathToFile);

        DataParser parser = new ParagraphParser();
        TextComponentInformation textComponent = parser.parse(text);
        TextComponentInformation sentences = textComponent.getChildren().get(0).getChildren().get(0);

        TextComponentService service = new TextComponentServiceImpl();
        service.countVowels(sentences);
        service.countConsonants(sentences);

        List<TextComponentInformation> list = service.findSentencesOfMaxWord(textComponent);

        service.removeSentencesWithMinWords(textComponent, 1);

        service.countEqualWords(textComponent, "its");
    }
}
