package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Common.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;

@CrossOrigin
@Controller
public class FileUploadController {

    @Autowired
    private FileUtil fileUtil;
    @Autowired
    ServletContext context;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://temp//";

    @GetMapping("/uploader")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload/{iType}") // //new annotation since 4.3
    public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable int iType) {
        String path = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\temp";
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(path + "\\" + fileName));
            fileUtil.SaveFile(iType, fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/fileList/")
    public Object GetFileList() {
        int[] counts = new int[3];
        counts[0] = fileUtil.GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\khs\\");
        counts[1] = fileUtil.GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\cy\\");
        counts[2] = fileUtil.GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\yh\\");
        return new ResponseEntity<>(counts, HttpStatus.OK);
    }


}