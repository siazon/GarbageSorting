package com.tus.garbagesorting.garbagesorting.Common;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FileUtil {

    /**
     * Save Picture for game
     *
     * @param iType
     * @param fileName
     */
    public void SaveFile(int iType, String fileName) throws IOException {
        String path = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\temp\\";
        String SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\";
        int count = 0;
        DetectLabels.detectLabels(path + fileName);

        WASTETYPE eType = WASTETYPE.fromInteger(iType);
        switch (eType) {
            case Recycle:
                count = GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\khs\\");
                SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\khs\\";
                break;
            case Organic:
                count = GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\cy\\");
                SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\cy\\";
                break;
            case Trash:
                count = GetFiles(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\yh\\");
                SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\yh\\";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + eType);
        }
        count++;
        if (count > 0)
            ChangeImgSize(path, fileName, SavePath, count + ".png");
    }

    /**
     * get files count
     *
     * @param fileDir
     * @return
     */
    public int GetFiles(String fileDir) {
        File file = new File(fileDir);
        File[] files = file.listFiles();
        return files.length;
    }

    /**
     * test
     *
     * @param fileDir
     */
    private void test2(String fileDir) {
        List<File> fileNew = new ArrayList<File>();
        File file = new File(fileDir);
        //get fileList
        File[] files = file.listFiles();
        // if dir empty return
        if (files == null) {
            return;
        }
        // get curr Dir files
        for (File f : files) {
            if (f.isFile()) {
                fileNew.add(f);
            } else if (f.isDirectory()) {
                //获取文件夹下子文件夹路径 并递归
                System.out.println(f.getAbsolutePath());
                test2(f.getAbsolutePath());
            }
        }
        for (File f1 : fileNew) {
            //get files
            System.out.println(f1.getName());
        }
    }

    /**
     * ChangeImgSize
     *
     * @param path
     * @param fileName
     */
    public void ChangeImgSize(String path, String fileName, String savePath, String saveFieName) {
        try {
            //读取原始图片
            BufferedImage image = ImageIO.read(new FileInputStream(path + fileName));
            System.out.println("Width: " + image.getWidth());
            System.out.println("Height: " + image.getHeight());
            //Resize
            BufferedImage newImage = ImageUtils.resizeImage(image, 100, 100);
            //
            ImageIO.write(newImage, "png", new File(savePath + saveFieName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
