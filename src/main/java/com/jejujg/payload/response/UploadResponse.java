package com.jejujg.payload.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UploadResponse {

    private Long fid;
    private String uuid;
    private String path;
    private String fileName;

//    @Builder
//    public UploadResponse(Long fid, String uuid, String path, String fileName) {
//        this.fid = fid;
//        this.uuid = uuid;
//        this.path = path;
//        this.fileName = fileName;
//    }
}
