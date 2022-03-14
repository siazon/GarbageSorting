package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Service.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@CrossOrigin
@Controller
public class FileUploadController {

    @Autowired
    ServletContext context;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "D://temp//";

    @GetMapping("/uploader")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload/{type}") // //new annotation since 4.3
    public ResponseEntity<?> singleFileUpload(@RequestParam("file") MultipartFile file, @PathVariable String type) {
        String iType = type;
        String path = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\temp";
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(path + "\\" + fileName));
            ChangeImgSize(path, fileName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    public void ChangeImgSize(String path, String fileName) {
        try {
            //读取原始图片
            BufferedImage image = ImageIO.read(new FileInputStream(path + "\\" + fileName));
            System.out.println("Width: " + image.getWidth());
            System.out.println("Height: " + image.getHeight());
            //Resize
            BufferedImage newImage = ImageUtils.resizeImage(image, 100, 100);
            //
            String _path = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\temp";
            ImageIO.write(newImage, "png", new File(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}