package com.example.data_api.api.file;


import com.example.data_api.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {

    private FileUtil fileUtil;

    @Value("${file.download-url}")
    private String fileDownloadUrl;


    //inject by autowired
    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void deleteByName(String name) {
        fileUtil.deleteByName(name);
    }

    @Override
    public Resource download(String name) {
        return fileUtil.findByName(name);
    }

    @Override
    public FileDto findByName(String name) throws IOException {
        Resource resource = fileUtil.findByName(name);

        return FileDto.builder()
                .name(resource.getFilename())
                .extension(fileUtil.getExtension(resource.getFilename()))
                .downloadUrl(String.format("%s%s", fileDownloadUrl, name))
                .url(String.format("%s%s", fileUtil.getFileBaseUrl(), resource.getFilename()))
                .size(resource.contentLength())
                .build();
    }

    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);
    }


    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {

        List<FileDto> filesDto = new ArrayList<>();

        for (MultipartFile file : files){
            filesDto.add(fileUtil.upload(file));
        }

        return filesDto;
    }
}
