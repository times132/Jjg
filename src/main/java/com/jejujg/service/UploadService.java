package com.jejujg.service;

import com.jejujg.model.Image;
import com.jejujg.payload.response.ImageResponse;
import com.jejujg.repository.UploadRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UploadService {

    @Value("${spring.uploadFolderPath}")
    private String uploadPath;
    private final UploadRepository uploadRepository;
    private static final Logger logger = LoggerFactory.getLogger(UploadService.class);

    public Map<String, Object> uploadImage(MultipartFile[] uploadFileList, String categoryNum){
        Map<String, Object> map = new HashMap<>();
        List<ImageResponse> imageList = new ArrayList<>();
        int notImageCount = 0;
        String uploadFolder = uploadPath;
        String uploadFolderPath = "board/" + categoryNum;

        File uploadPath = new File(uploadFolder, uploadFolderPath);

        if (!uploadPath.exists()){
            uploadPath.mkdirs();
        }

        for (MultipartFile uploadFile : uploadFileList){
            String uploadOriFileName = uploadFile.getOriginalFilename();
            uploadOriFileName = uploadOriFileName.substring(uploadOriFileName.lastIndexOf("\\") + 1); // IE file path

            UUID uuid = UUID.randomUUID();
            String uploadFileName = uuid + "_" + uploadOriFileName;
            try {
                File saveFile = new File(uploadPath, uploadFileName);
                uploadFile.transferTo(saveFile);

                if (checkImage(saveFile)) { // 이미지 파일일 때
//                uploadFile.transferTo(saveFile);
                    logger.info("uploadPath.getAbsolutePath()" + uploadPath.getAbsolutePath());
                    makeThumbnail(uploadPath.getAbsolutePath(), uploadFileName, uploadFileName.substring(uploadFileName.lastIndexOf(".")+1), 480, 480);
                    logger.info("썸네일 생성 완료");
                    imageList.add(
                            ImageResponse.builder()
                                    .fileName(uploadOriFileName)
                                    .path(uploadFolderPath)
                                    .uuid(uuid.toString())
                                    .build()
                    );
                } else{ // 이미지 파일이 아닐 때
                    notImageCount++;
                }
            } catch (Exception e){ //
                e.printStackTrace();
            }
        }
        map.put("imageList", imageList);
        map.put("notImageCount", notImageCount);

        return map;
    }

//    public Map<String, Object> uploadMultiImage(MultipartFile[] uploadFile, String categoryNum){
//        Map<String, Object> map = new HashMap<>();
//        String uploadFolder = uploadPath;
//        String uploadFolderPath = "board/" + categoryNum;
//
//        File uploadPath = new File(uploadFolder, uploadFolderPath);
//
//        if (!uploadPath.exists()){
//            uploadPath.mkdirs();
//        }
//
//        for (MultipartFile multipartFile : uploadFile){
//            String uploadOriFileName = multipartFile.getOriginalFilename();
//            uploadOriFileName = uploadOriFileName.substring(uploadOriFileName.lastIndexOf("\\") + 1); // IE file path
//
//            UUID uuid = UUID.randomUUID();
//            String uploadFileName = uuid + "_" + uploadOriFileName;
//            try {
//                File saveFile = new File(uploadPath, uploadFileName);
//                multipartFile.transferTo(saveFile);
//                logger.info("원본 생성 완료");
//                if (checkImage(saveFile)) { // 이미지 파일일 때
////                uploadFile.transferTo(saveFile);
//                    logger.info("uploadPath.getAbsolutePath()" + uploadPath.getAbsolutePath());
//                    makeThumbnail(uploadPath.getAbsolutePath(), uploadFileName, uploadFileName.substring(uploadFileName.lastIndexOf(".")+1), 480, 480);
//                    logger.info("썸네일 생성 완료");
//
//                    map.put("path", uploadFolderPath);
//                    map.put("fileName", uploadOriFileName);
//                    map.put("uuid", uuid);
//                    map.put("isImage", true);
//                } else{ // 이미지 파일이 아닐 때
//                    map.put("isImage", false);
//                }
//            } catch (Exception e){ //
//                e.printStackTrace();
//            }
//        }
//
//        return map;
//    }

    public void deleteGoodsImage(String fileName, String categoryPath){
        File file;

        try {
            file = new File(uploadPath + "/" + categoryPath + "/" + URLDecoder.decode(fileName, "UTF-8"));
            file.delete();

            file = new File(uploadPath + "/" + categoryPath + "/s_" + URLDecoder.decode(fileName, "UTF-8"));
            file.delete();
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
    }

    public List<Image> saveGoodsImageDB(List<Image> imageResponseList){
        List<Image> imageList = new ArrayList<>();
        for (Image image : imageResponseList) {
            imageList.add(uploadRepository.save(image));
        }
        return imageList;
    }

    public Image updateGoodsImageDB(Long fid, Map<String, Object> imageMap){
        return null;
//        return uploadRepository.save(convertMapToImage(fid, imageMap));
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
            }else {
                file.delete();
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    private String getFolder(Long userid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = userid + "-" + sdf.format(date);
        return str.replace("-", "/");
    }

    private Image convertMapToImage(Long fid, ImageResponse imageResponse){
        return Image.builder()
                .fid(fid)
                .fileName(imageResponse.getFileName())
                .path(imageResponse.getPath())
                .uuid(imageResponse.getUuid())
                .build();
    }
}
