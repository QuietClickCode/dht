package com.retailers.tools.utils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    /**
     * 生产文件 如果文件所在路径不存在则生成路径
     *
     * @param fileName
     *            文件名 带路径
     * @param isDirectory 是否为路径
     * @return
     */
    public static File buildFile(String fileName, boolean isDirectory) {

        File target = new File(fileName);

        if (isDirectory) {
            target.mkdirs();
        } else {
            if (!target.getParentFile().exists()) {
                target.getParentFile().mkdirs();
                target = new File(target.getAbsolutePath());
            }
        }

        return target;
    }

    public static List<String> listFile(String dirName) {

        List<String> fileList = new ArrayList<String>();

        listFile(new File(dirName), fileList);

        return fileList;
    }

    private static void listFile(File dirFile, List<String> fileList) {

        if (dirFile.isDirectory()) {
            File[] fs = dirFile.listFiles();
            for(int i=0; i<fs.length; i++) {
                String filePath = fs[i].getAbsolutePath();
                //macos 系统压缩的文件夹包换有 __MACOSX
                if (filePath.contains("__MACOSX")) {
                    continue;
                }
                // 用于Microsoft Windows XP或mac os x缓存Windows Explorer的缩略图的文件
                if (filePath.endsWith("Thumbs.db")) {
                    continue;
                }
                if (fs[i].isDirectory()) {
                    listFile(fs[i], fileList);
                } else if (fs[i].isFile()) {
                    fileList.add(convertFilePathToUnix(fs[i].getAbsolutePath()));
                }
            }
        }
    }

    /**
     * 使用文件通道的方式复制文件
     *
     * @param s 源文件
     * @param t 复制到的新文件
     */
    public static void fileChannelCopy(File s, File t) {

        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);

            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道

            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件夹拷贝
     * @param sourceDir
     * @param targetDir
     * @throws IOException
     */
    public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
        // 新建目标目录
        (new File(targetDir)).mkdirs();
        // 获取源文件夹当前下的文件或目录
        File[] file = (new File(sourceDir)).listFiles();
        for (int i = 0; i < file.length; i++) {
            if (file[i].isFile()) {
                // 源文件
                File sourceFile = file[i];
                // 目标文件
                File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
                fileChannelCopy(sourceFile, targetFile);
            } else if (file[i].isDirectory()) {
                // 准备复制的源文件夹
                String dir1 = sourceDir + "/" + file[i].getName();
                // 准备复制的目标文件夹
                String dir2 = targetDir + "/" + file[i].getName();
                copyDirectiory(dir1, dir2);
            }
        }
    }

    /**
     * 删除文件夹
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹路径下所有文件
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    public static String convertFilePathToUnix(String filePath) {
        return filePath.replace("\\", "/");
    }

    /**
     * 下载 excel
     *
     * @param path
     * @param response
     */
    public static void download(String path, HttpServletResponse response,String application) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = response.encodeURL(new String(file.getName().getBytes(), "ISO8859_1"));//转码，解决文件名中有中文出现无法下载的情况  ;
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + filename);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType(application);
            response.setCharacterEncoding("UTF-8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
            //下载完删除文件
            // 判断目录或文件是否存在
//            if (file.exists()) {  // 不存在返回 false
//                file.delete();
//            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
