package com.apps.wordee.controller;

import com.apps.wordee.helper.TestHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class WordeeControllerTest extends TestHelper {
    @Autowired
    WordeeController wordeeController;

    @Test
    public void shouldRejectNonTextFile() throws Exception {
        MultipartFile imageFile = createMultipartFile("image-file.jpeg", "image/jpeg");

        Assertions.assertThrows(Exception.class, () -> {
            wordeeController.analyseWords(imageFile);
        });
    }

}
