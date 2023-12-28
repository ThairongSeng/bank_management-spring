package com.example.data_api.api.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**
     * use to upload a single file
     * @param file request form data from client
     * @return FileDto
     */
    FileDto uploadSingle(MultipartFile file);



    /**
     * use to upload a many files
     * @param files request form data from client
     * @return FileDto
     */
    List<FileDto> uploadMultiple(List<MultipartFile> files);
}
