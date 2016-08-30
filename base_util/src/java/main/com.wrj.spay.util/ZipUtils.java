package com.wrj.spay.util;

import com.wrj.spay.exception.BizRuntimeException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

/**
 * 压缩包工具类
 */
public class ZipUtils {

    public static void zip(String srcPathName, String zipFilePath) throws RuntimeException {
        File file = new File(srcPathName);
        if (!file.exists())
            throw new BizRuntimeException(ZipUtils.class, "source file or directory=[%s] does not exist.", srcPathName);

        Project proj = new Project();
        FileSet fileSet = new FileSet();
        fileSet.setProject(proj);

        if (file.isDirectory()) {
            fileSet.setDir(file);
        } else {
            fileSet.setFile(file);
        }

        Zip zip = new Zip();
        zip.setProject(proj);
        zip.setDestFile(new File(zipFilePath));
        zip.addFileset(fileSet);
        zip.execute();
    }

    public static boolean unZip(String zipFilePath, String destDir) throws RuntimeException {

        if (!new File(zipFilePath).exists())
            throw new RuntimeException("zip file " + zipFilePath + " does not exist.");

        Project proj = new Project();
        Expand expand = new Expand();
        expand.setProject(proj);
        expand.setTaskType("unzip");
        expand.setTaskName("unzip");

        expand.setSrc(new File(zipFilePath));
        expand.setDest(new File(destDir));
        expand.execute();

        return true;
    }

}
