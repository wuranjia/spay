package com.wrj.spay.util;

import java.io.*;
import java.nio.charset.Charset;

public class FileUtils {


    public static File forceMkdir(String dirName) {
        return forceMkdir(new File(dirName));
    }

    public static File forceMkdir(File directory) {
        try {
            org.apache.commons.io.FileUtils.forceMkdir(directory);
            return directory;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static File copyFile(File src, File dest) {
        try {
            org.apache.commons.io.FileUtils.copyFile(src, dest);
            return dest;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static File copyFileToDirectory(File src, File dest) {
        try {
            org.apache.commons.io.FileUtils.copyFileToDirectory(src, dest);
            return dest;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static void writeByteToFile(String fullFileName, byte[] bytes) {
        BufferedOutputStream out =null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(fullFileName));
            out.write(bytes, 0, bytes.length);
        } catch (Exception e) {
            Logger.error(FileUtils.class,"error in writeByteToFile "+e.getMessage());
            throw new RuntimeException("error in writeByteToFile.", e);
        }finally {
            if (out!=null) {
                try {
                    out.close();
                }catch (Exception e){
                }
            }
        }
    }



    public static byte[] readByteFromFile(String fullFileName) {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(fullFileName));
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            in.close();

            byte[] content = out.toByteArray();
            //   System.out.println("Readed bytes count:" + content.length);
            return content;
        } catch (Exception e) {
            Logger.error(FileUtils.class,"error in readByteFromFile "+e.getMessage());
            throw new RuntimeException("error in readByteFromFile.", e);
        }
    }


    public static byte[] readByteFromStream(InputStream stream) {
        try {
            BufferedInputStream in = new BufferedInputStream(stream);
            ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
            byte[] temp = new byte[1024];
            int size = 0;
            while ((size = in.read(temp)) != -1) {
                out.write(temp, 0, size);
            }
            in.close();

            byte[] content = out.toByteArray();
            //   System.out.println("Readed bytes count:" + content.length);
            return content;
        } catch (Exception e) {
            Logger.error(FileUtils.class,"error in readByteFromFile "+e.getMessage());
            throw new RuntimeException("error in readByteFromFile.", e);
        }
    }



    public static String readStrFromFile(String fullFileName) {
        String lineSeparator = System.getProperty("line.separator");
        File jsonFile = new File(fullFileName);
        StringBuilder buf = new StringBuilder();
        if (jsonFile.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(jsonFile), Charset.forName("UTF-8")));
                String line = br.readLine();
                while (null != line) {
                    buf.append(line);
                    buf.append(lineSeparator);

                    line = br.readLine();
                }
                br.close();
            } catch (Exception e) {
                Logger.error(FileUtils.class,"error in readStrFromFile "+e.getMessage());
                throw new RuntimeException(String.format("Exception happened when read file(%s)", fullFileName), e);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        throw new RuntimeException(String.format("Exception happened when read file(%s)", fullFileName), e);
                    }
                }
            }
        }
        return buf.toString();
    }
}