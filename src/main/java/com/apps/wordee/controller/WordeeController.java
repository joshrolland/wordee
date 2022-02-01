package com.apps.wordee.controller;

import com.apps.wordee.processor.WordeeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.apache.commons.io.FilenameUtils;

@Validated
@RestController
public class WordeeController {
    @Autowired
    WordeeProcessor wordeeProcessor;

    @PostMapping("/analyseWords")
    public ResponseEntity<String> analyseWords(@RequestParam("filePath") MultipartFile file) throws Exception {
        if(!file.isEmpty() && FilenameUtils.getExtension(file.getOriginalFilename()).equals("txt")) {
            return new ResponseEntity<>(wordeeProcessor.process(file), HttpStatus.OK);
        } else {
            throw new Exception("Error processing file");
        }
    }

}