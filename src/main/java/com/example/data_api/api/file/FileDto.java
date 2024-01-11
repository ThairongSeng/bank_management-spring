package com.example.data_api.api.file;


import lombok.Builder;

@Builder
public record FileDto(String name,
                      String url,
                      String downloadUrl,
                      String extension,
                      long size
                      ) {
}
