package com.assessment.comsc.fileUpload;

import lombok.Data;

import java.util.Date;

@Data
public class FileInfo {


    private Integer id;

    private Integer assessmentId;

    private Long userId;

    //where is the file!
    private String url;

    private String fileName;

    private Date uploadTime;

}
