package com.example.data_api.api.file;


import com.example.data_api.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import java.util.List;


@Service
public class FileServiceImpl implements FileService {

    private FileUtil fileUtil;

    //inject by autowired
    @Autowired
    public void setFileUtil(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
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
