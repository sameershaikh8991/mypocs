package com.upload;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {
    public static File convertMultipartFileToFile(MultipartFile multipartFile ,String  prefix, String suffix) throws IOException {
        File convertedFile = File.createTempFile(prefix, "."+suffix);
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(multipartFile.getBytes());
        }
        return convertedFile;
    }
}
