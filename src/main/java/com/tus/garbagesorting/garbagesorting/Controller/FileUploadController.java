package com.tus.garbagesorting.garbagesorting.Controller;

import com.tus.garbagesorting.garbagesorting.Common.FileUtil;
import com.tus.garbagesorting.garbagesorting.Mapper.PictureMapper;
import com.tus.garbagesorting.garbagesorting.Model.PictureInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@Controller
public class FileUploadController {

    @Autowired
    private PictureMapper pictureMapper;
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
    public ResponseEntity<Map<String, Object>> singleFileUpload(@RequestParam("file") MultipartFile file,
                                                                @PathVariable int iType) {
        Map<String, Object> map = new HashMap<>();
        String path = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\temp";
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(path + "\\" + fileName));
            fileUtil.SaveFile(iType, fileName);
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", "unknown error");
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
        }

        map.put("state", true);
        map.put("msg", "File uploaded successfully.");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @GetMapping("/fileList/")
    public Object GetFileList() {
        List<String> files = new ArrayList<>();
        var temp = fileUtil.GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\recycle\\");
        int i = 0;
        for (String path : temp) {
            files.add(path.substring(path.indexOf("img")));
            i++;
            if (i > 15) break;// limit 15
        }

        temp = fileUtil.GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\organic\\");
        i = 0;
        for (String path : temp) {
            files.add(path.substring(path.indexOf("img")));
            i++;
            if (i > 15) break; //limit 15
        }
        temp = fileUtil.GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\trash\\");
        i = 0;
        for (String path : temp) {
            files.add(path.substring(path.indexOf("img")));
            i++;
            if (i > 15) break; //limit 15
        }
        temp = fileUtil.GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\todo\\");
        i = (int) (files.size() * 0.1);
        for (String path : temp) {
            files.add(path.substring(path.indexOf("img")));
            i--;
            if (i <= 0) break; //limit 15
        }
        return new ResponseEntity<List<String>>(files, HttpStatus.OK);
    }

    @PostMapping("/UploadUserImgState") // //new annotation since 4.3
    public ResponseEntity<Map<String, Object>> UploadUserImgState(@RequestBody PictureInfo pic) {
        Map<String, Object> map = new HashMap<>();
        pictureMapper.upset(pic);
        CalcRate(pic.getPath());
        map.put("state", true);
        map.put("msg", "File uploaded successfully.");
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    /**
     * official material
     *
     * @param imgName
     */
    private void CalcRate(String imgName) {
        List<PictureInfo> infos = pictureMapper.findByPath(imgName);
        double sum = 0;
        double Confidence = 0.95;
        //Get total number of the sorting
        for (int i = 0; i < infos.size(); i++) {
            sum += infos.get(i).getSort_times();
        }
        for (int i = 0; i < infos.size(); i++) {
            //Get ratio
            double rate = infos.get(i).getSort_times() / sum;
            if (rate >= Confidence) {
                UpdateImageState(infos.get(i));
                break;//Jump out in time to reduce loops
            }
        }
    }


    private void UpdateImageState(PictureInfo info) {
        fileUtil.CopyImage(info.getPath(), info.getType());
    }

}