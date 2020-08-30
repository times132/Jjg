package com.jejujg.controller;

import com.jejujg.payload.response.UploadResponse;
import com.jejujg.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(value = "/goods")
    public ResponseEntity<UploadResponse> saveGoodsImage(@RequestPart MultipartFile file) {
        UploadResponse uploadResponse = UploadResponse.builder()
                .fileName(file.getOriginalFilename())
                .path("D://upload")
                .uuid(UUID.randomUUID().toString())
                .build();

        return new ResponseEntity<>(uploadResponse, HttpStatus.OK);
    }
}
