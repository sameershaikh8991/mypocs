package com.upload;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class FileUploadController {
    @Autowired
    private UploadedFileRepository uploadedFileRepository;

    @Autowired
    private TikaService tikaService;

    @Autowired
    private LuceneIndexerService luceneIndexerService;

    @Autowired
    private LuceneSearchService luceneSearchService;

    @Value("${upload.directory}")
    private String uploadDirectory;


    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        if(file.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please select a file.");
        }

        try {
            UploadedFile existingFile = uploadedFileRepository.findByFileName(file.getOriginalFilename());
            if (existingFile != null) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("File with the same name already exists.");
            }
            Path filePath = Paths.get(uploadDirectory, file.getOriginalFilename());
            Files.write(filePath, file.getBytes());

            String originalFilename = file.getOriginalFilename();
            String prefix = FilenameUtils.getBaseName(originalFilename);
            String suffix = FilenameUtils.getExtension(originalFilename);

            String type = file.getContentType().split("/")[0];

            String content =null;

            if(type.equals("image")){
                 File file1 = FileUtils.convertMultipartFileToFile(file, prefix, suffix);
                 content = tikaService.extractTextImg(file1);
                System.out.println("content : "+content);
            }
            else{
                content = tikaService.extractText(file.getInputStream());
            }
            UploadedFile uploadedFile = new UploadedFile();
            uploadedFile.setFileName(file.getOriginalFilename());
            uploadedFileRepository.save(uploadedFile);
            long recordId = uploadedFile.getId();

            luceneIndexerService.indexDocument(content, file.getOriginalFilename(),recordId);
            return ResponseEntity.ok("File uploaded successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file.");
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<String>> searchDocuments(@RequestParam("search") String query) {
        List<String> fileNames = luceneSearchService.searchFilesByContent(query);
        return ResponseEntity.ok(fileNames);
    }
}
