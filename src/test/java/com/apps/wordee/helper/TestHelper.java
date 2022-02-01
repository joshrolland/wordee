package com.apps.wordee.helper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

public class TestHelper {
    public MultipartFile createMultipartFile(String classPathLocation, String fileType) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(classPathLocation);

        final byte[] content = classPathResource.getInputStream().readAllBytes();
        return new MockMultipartFile(
                Objects.requireNonNull(classPathResource.getFilename()),
                classPathResource.getFilename(),
                fileType,
                content);
    }
}
