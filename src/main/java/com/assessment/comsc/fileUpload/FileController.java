package com.assessment.comsc.fileUpload;

import com.assessment.comsc.dto.DataResult;
import com.assessment.comsc.service.UserInfoService;
import com.assessment.comsc.tool.AuthorityTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private UserInfoService userInfoService;

    @GetMapping("/uploadPage")
    public String hello() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public DataResult uploadFile(@RequestParam("file") MultipartFile file,
                                 @RequestParam("assessmentId") Integer assessmentId) {


        DataResult<Object> result = new DataResult<>();

        if (file.isEmpty()) {
            result.failed(null, "no file");
            return result;
        }


        try {
            // Get the file storage directory
            File directory = new File("src/main/resources/uploadFileLoca");
            if (!directory.exists()) {

                //If the directory does not exist, create a directory
                directory.mkdirs();
            }

            // Get the file name
            String fileName = file.getOriginalFilename();

            // Build the file path
            String filePath = directory.getAbsolutePath() + File.separator + fileName;

            // Save the file
            file.transferTo(new File(filePath));

            FileInfo fileInfo = new FileInfo();
            fileInfo.setAssessmentId(assessmentId);


            String userName;
            try {
                userName = AuthorityTool.getUserName();
            } catch (Exception e) {
                result.failed(null, e.getMessage());
                return result;
            }

            //search the userID.
            fileInfo.setUserId(userInfoService.getUserInfoByMail(userName).getUserId());
            fileInfo.setUrl(filePath);
            fileInfo.setFileName(fileName);
            fileInfo.setUploadTime(new Date());

            //insert the file
            fileInfoRepository.insertFileInfo(fileInfo);

            result.succeed(null);
            return result;
        } catch (IOException e) {

            result.failed(null, e.getMessage());
            return result;
        }

    }

}