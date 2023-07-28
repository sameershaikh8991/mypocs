package com.upload;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadedFileRepository extends JpaRepository<UploadedFile, Long> {

    UploadedFile findByFileName(String fileName);

}
