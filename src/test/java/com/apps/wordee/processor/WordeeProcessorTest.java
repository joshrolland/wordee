package com.apps.wordee.processor;

import com.apps.wordee.helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class WordeeProcessorTest extends TestHelper {
    @Autowired
    WordeeProcessor wordeeProcessor = new WordeeProcessor();

    @Test
    public void shouldReturnCorrectResponseFromPopulatedTextFile() throws Exception {
        MultipartFile textFile = createMultipartFile("text-file.txt", "text/plain");

        String responseString = "Word count = 9\n" +
                "Average word length = 4.556\n" +
                "Number of words of length 1 is 1\n" +
                "Number of words of length 2 is 1\n" +
                "Number of words of length 3 is 1\n" +
                "Number of words of length 4 is 2\n" +
                "Number of words of length 5 is 2\n" +
                "Number of words of length 7 is 1\n" +
                "Number of words of length 10 is 1\n" +
                "The most frequently occurring word length is 2, for word lengths of 4 & 5";

        Assertions.assertEquals(responseString, wordeeProcessor.process(textFile));
    }

    @Test
    public void shouldReturnCorrectResponseFromUnpopulatedTextFile() throws Exception {
        MultipartFile textFile = createMultipartFile("nontext-file.txt", "text/plain");
        String responseString = "No words found in text file";

        Assertions.assertEquals(responseString, wordeeProcessor.process(textFile));
    }

}
