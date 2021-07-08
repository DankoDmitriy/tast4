package com.danko.information_handling.reader;

import com.danko.information_handling.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReader {
    private static Logger logger = LogManager.getLogger();

    public String readText(String pathToFile) throws TextException {
        if (pathToFile == null) {
            throw new TextException("Input file paths is NULL...");
        }
        try {
            String text = Files.readString(Paths.get(pathToFile));
            logger.log(Level.INFO, "Text has been read... from " + pathToFile);
            return text;
        } catch (IOException e) {
            logger.log(Level.ERROR, "File has not been read..." + e);
            throw new TextException("File has not been read...", e);
        }
    }
}
