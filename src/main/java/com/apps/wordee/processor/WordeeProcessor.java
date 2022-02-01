package com.apps.wordee.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WordeeProcessor {

    String ALLOWED_CHARACTERS = "[^A-Za-z0-9:&/\\s]";

    public String process(MultipartFile file) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(file.getInputStream())
        );

        ArrayList<String> words = new ArrayList<>(Arrays.asList(
                        reader.lines().collect(Collectors.joining()).
                        replaceAll(ALLOWED_CHARACTERS, "")
                        .split("\\s+")));

        if (words.get(0).length() > 0){
            return responseBuilder(words);
        } else {
            return "No words found in text file";
        }
    }

    private String responseBuilder(ArrayList<String> words) {
        HashMap<Integer, Integer> wordLengths = getWordLengths(words);

        String response = "Word count = " + words.size() + "\n" +
                "Average word length = " + averageWordLength(words) + "\n";

        response = response + getNumberOfWordsOfLengthString(wordLengths)
                + getMostFrequentWordLengthString(wordLengths);

        return response;
    }

    private HashMap<Integer, Integer> getWordLengths(ArrayList<String> words) {
        HashMap<Integer, Integer> wordLengths = new HashMap<>();

        for (String word : words) {
            if (wordLengths.containsKey(word.length())) {
                wordLengths.put(word.length(), wordLengths.get(word.length()) + 1);
            } else {
                wordLengths.put(word.length(), 1);
            }
        }

        return wordLengths;
    }

    private String averageWordLength(ArrayList<String> words) {
        int count = 0;
        double sum = 0;
        for (String word : words) {
            double wordLength = word.length();
            sum += wordLength;
            count++;
        }

        double average = 0;
        if (count > 0) {
            average = sum / count;
        }

        return String.format("%.3f", average);
    }

    private String getNumberOfWordsOfLengthString(HashMap<Integer, Integer> wordLengths) {
        String wordLengthsString = "";

        for (Map.Entry<Integer, Integer> length : wordLengths.entrySet()) {
            wordLengthsString = wordLengthsString + "Number of words of length " + length.getKey() + " is " + length.getValue() + "\n";
        }

        return wordLengthsString;
    }

    private String getMostFrequentWordLengthString(HashMap<Integer, Integer> wordLengths) {

        Integer highestFrequency = Collections.max(wordLengths.values());

        List<Integer> mostFrequentWordLengths = wordLengths.entrySet().stream()
                .filter(entry -> entry.getValue() == highestFrequency)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());

        String mostFrequentWordLengthString = "The most frequently occurring word length is "
                + highestFrequency + ", for word lengths of ";

        int index = 0;
        for (Integer length : mostFrequentWordLengths) {
            if (index == 0) {
                mostFrequentWordLengthString = mostFrequentWordLengthString + length;
            } else if (index == (mostFrequentWordLengths.size() - 1)) {
                mostFrequentWordLengthString = mostFrequentWordLengthString + " & " + length;
            } else {
                mostFrequentWordLengthString = mostFrequentWordLengthString + ", " + length;
            }
            index++;
        }

        return mostFrequentWordLengthString;
    }

}