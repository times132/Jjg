package com.jejujg.controller;

import com.jejujg.model.Image;
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

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping(value = "/goods")
    public ResponseEntity<?> saveGoodsImage(@RequestPart MultipartFile file, String categoryNum) {
        Map<String, Object> imageMap = uploadService.uploadGoods(file, categoryNum);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
