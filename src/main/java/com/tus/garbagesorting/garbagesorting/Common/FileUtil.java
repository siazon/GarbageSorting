package com.tus.garbagesorting.garbagesorting.Common;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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


        WASTETYPE eType = WASTETYPE.fromInteger(iType);
        switch (eType) {
            case Recycle:
                count = GetFilescount(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\recycle\\");
                SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\recycle\\";
                break;
            case Organic:
                count = GetFilescount(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\organic\\");
                SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\organic\\";
                break;
            case Trash:
                count = GetFilescount(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\trash\\");
                SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\trash\\";
                break;
            default:
                count = GetFilescount(new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\todo\\");
                SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\todo\\";
                break;
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
    public int GetFilescount(String fileDir) {
        File file = new File(fileDir);
        File[] files = file.listFiles();
        return files.length;
    }

    /**
     * get files count
     *
     * @param fileDir
     * @return
     */
    public List<String> GetFiles(String fileDir) {
        File file = new File(fileDir);
        File[] files = file.listFiles();
        List<String> filePaths = new ArrayList<>();
        for (File _file : files) {
            filePaths.add(_file.getPath());
        }
        return filePaths;
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

    public void CopyImage(String src, String gType) {
        try {
            String SavePath = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\";
            String _path = src.substring(0, src.lastIndexOf('_'));
            _path = _path.replace('_', '\\');
            String fileName = src.substring(src.lastIndexOf('_') + 1);
            src = SavePath + _path + "." + fileName;

            int Qty = GetFilescount(SavePath + "img\\" + gType + "\\");
            String dst = SavePath + "img\\" + gType + "\\" + ++Qty + "." + fileName;

            Path srcPath = Paths.get(src);
            Path dstPath = Paths.get(dst);
            Files.copy(srcPath, dstPath, StandardCopyOption.REPLACE_EXISTING);
            Files.delete(srcPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
