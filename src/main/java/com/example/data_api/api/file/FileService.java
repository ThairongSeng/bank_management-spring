package com.example.data_api.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {

    void deleteByName(String name);


    Resource download(String name);


    /**
     * use to find file by name
     * @param name of file
     * @return FileDto
     * @throws IOException internal error
     */
    FileDto findByName(String name) throws IOException;


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
