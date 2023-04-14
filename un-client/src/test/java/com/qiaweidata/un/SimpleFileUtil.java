package com.qiaweidata.un;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title: SimpleFileUtil
 * @Description: SimpleFileUtil
 * @Company: www.wrenchdata.com
 * @author: shenshilong
 * @date: 2023-04-14
 * @version: V1.0
 */
public class SimpleFileUtil {

    public static List<File> files(String path) {

        List<File> files = new ArrayList<>(10);
        allFiles(new File(path), files);
        return files;
    }


    public static void allFiles(File parentFile, List<File> files) {

        if (null == parentFile) {
            return;
        }
        File[] fs = parentFile.listFiles();
        if (null != fs) {
            for (int i = 0; i < fs.length; i++) {
                allFiles(fs[i], files);
            }
        }
        if (parentFile.isFile()) {
            files.add(parentFile);
        }
    }
}
