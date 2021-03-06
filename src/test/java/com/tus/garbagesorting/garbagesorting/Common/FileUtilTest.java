package com.tus.garbagesorting.garbagesorting.Common;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.FileSystemResource;

public class FileUtilTest {
    @Test
    void GetFiles() throws Exception {
        String path = new FileSystemResource("").getFile().getAbsolutePath() + "\\frontend\\img\\yh";
        FileUtil util = new FileUtil();
        util.GetFiles(path);
    }

    @Test
    void TestCopyFile() throws Exception {
        FileUtil util = new FileUtil();
        util.CopyImage("img_todo_1_png", "recycle");
    }
}