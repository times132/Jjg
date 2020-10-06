package com.jejujg.service;

import com.jejujg.model.Image;
import com.jejujg.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UploadService {

    private final UploadRepository uploadRepository;

    public Map<String, Object> uploadGoods(MultipartFile uploadFile, String categoryNum){
        Map<String, Object> map = new HashMap<>();
        String uploadFolder = "D:\\jjg-upload";
        String uploadFolderPath = "goods/" + categoryNum;

        File uploadPath = new File(uploadFolder, uploadFolderPath);

        if (!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        String uploadOriFileName = uploadFile.getOriginalFilename();
        uploadOriFileName = uploadOriFileName.substring(uploadOriFileName.lastIndexOf("\\") + 1); // IE file path

        UUID uuid = UUID.randomUUID();
        String uploadFileName = uuid + "_" + uploadOriFileName;
        try {
            File saveFile = new File(uploadPath, uploadFileName);
            uploadFile.transferTo(saveFile);

            if (checkImage(saveFile)) { // 이미지 파일일 때
                makeThumbnail(uploadPath.getAbsolutePath(), uploadFileName, uploadFileName.substring(uploadFileName.lastIndexOf(".")+1), 480, 480);
                map.put("path", uploadFolderPath);
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

    public void deleteGoods(){

    }

    public Image saveGoodsDB(Map<String, Object> imageMap){
        return uploadRepository.save(convertMapToImage(null, imageMap));
    }

    public Image updateGoodsDB(Long fid, Map<String, Object> imageMap){
        return uploadRepository.save(convertMapToImage(fid, imageMap));
    }


    // 썸네일 생성
    private void makeThumbnail(String filePath, String fileName, String fileExt, int height, int width) throws Exception{
        BufferedImage srcImg = ImageIO.read(new File(filePath+ "/" + fileName));

        // 썸네일 사이즈
        int tw = width;
        int th = height;

        //파일 사이즈
        int fw = srcImg.getWidth();
        int fh = srcImg.getHeight();

        // 썸네일 사이즈만큼 빈 픽셀에 하얀배경 추가
        if (fw != fh){
            int pd = 0;
            if (fw > fh){
                pd = (int)(Math.abs((th * fw / (double)tw) - fh) / 2d);
            } else{
                pd = (int)(Math.abs((tw * fh / (double)th) - fw) / 2d);
            }
            srcImg = Scalr.pad(srcImg, pd, Color.WHITE, Scalr.OP_ANTIALIAS);

            fw = srcImg.getWidth();
            fh = srcImg.getHeight();
        }

        int nw = fw;
        int nh = (fw * th) / tw;
        if (nh > fh){
            nw = (fh * tw) / th;
            nh = fh;
        }

        BufferedImage cropImg = Scalr.crop(srcImg, (fw-nw)/2, (fh-nh)/2, nw, nh);
        BufferedImage destImg = Scalr.resize(cropImg, tw, th);

        String thumbName = filePath + "/s_" + fileName;
        File thumbFile = new File(thumbName);
        ImageIO.write(destImg, fileExt.toUpperCase(), thumbFile);
    }

    // 파일 mimeType 체크
    private boolean checkImage(File file){
        try {
            String mimeType = new Tika().detect(file);
            if (mimeType.startsWith("image")){
                return true;
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    private Image convertMapToImage(Long fid, Map<String, Object> imageMap){
        return Image.builder()
                .fid(fid)
                .fileName(String.valueOf(imageMap.get("fileName")))
                .path(String.valueOf(imageMap.get("path")))
                .uuid(String.valueOf(imageMap.get("uuid")))
                .build();
    }
}
