package com.company.ellRes.fileService;


import com.company.ellRes.dataService.dataFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class saveFileDocument {

    @Value("${upload.path.document}")
    private String uploadPathDocument;

    @Autowired
    private dataFile dataFile;


    public String uploadDocument(MultipartFile file) throws IOException {
        File uploadDir = new File(this.uploadPathDocument + "/" + dataFile.geteTodayYer());
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + "." + file.getOriginalFilename();
        file.transferTo(new File(this.uploadPathDocument + "/" + dataFile.geteTodayYer() + "/" + resultFileName));
        return resultFileName;
    }

}
