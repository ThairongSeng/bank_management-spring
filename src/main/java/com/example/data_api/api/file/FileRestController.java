package com.example.data_api.api.file;


import com.example.data_api.base.BaseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/files")
public class FileRestController {


    private final FileService fileService;

    @PostMapping
    public BaseRest<?> uploadSingle(@RequestPart("file") MultipartFile file){
        FileDto fileDto = fileService.uploadSingle(file);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }


    @PostMapping("/multiple")
    public BaseRest<?> uploadMultiple(@RequestPart("files") List<MultipartFile> files){
        List<FileDto> fileDto = fileService.uploadMultiple(files);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been uploaded")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
}
