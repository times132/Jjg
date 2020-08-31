package com.jejujg.service;

import com.jejujg.model.Image;
import com.jejujg.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UploadService {

    private final UploadRepository uploadRepository;

    public Map<String, Object> uploadGoods(MultipartFile uploadFile, String categoryNum){
        Map<String, Object> map = new HashMap<>();
        String uploadFolder = "D:\\jjg";
        String uploadFolderPath = "goods/" + categoryNum;

        File uploadPath = new File(uploadFolder, uploadFolderPath);

        if (!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        String uploadOriFileName = uploadFile.getOriginalFilename();
        uploadOriFileName = uploadOriFileName.substring(uploadOriFileName.lastIndexOf("\\") + 1); // IE file path

        UUID uuid = UUID.randomUUID();
        String uploadFileName = uuid.toString() + "_" + uploadOriFileName;
        try {
            File saveFile = new File(uploadPath, uploadFileName);
            uploadFile.transferTo(saveFile);

            if (checkImage(saveFile)) { // 이미지 파일일 때
                map.put("uploadPath", uploadFolderPath);
                map.put("fileName", uploadOriFileName);
                map.put("uuid", uuid);
                map.put("isImage", true);
            } else{ // 이미지 파일이 아닐 때
                map.put("isImage", false);
            }
        } catch (Exception e){ //
            e.printStackTrace();
        }

        return map;
    }

    private boolean checkImage(File file){
        try {
            String mimeType = new Tika().detect(file);
            log.info("MimeType: ", mimeType);
            if (mimeType.startsWith("image")){
                return true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }
}
