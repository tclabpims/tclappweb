package com.tcl.controller;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LiuQi on 2017/8/4.
 */

@Controller
@RequestMapping(value = "/kindedit")
public class KindEditorController {
    // 日志输出对象
    private static Log log = LogFactory.getLog(KindEditorController.class);
    // 文件后缀名称
    private String fileExt;
    //文件目录名称
    private String fileDir;
    //当前站点上下文
    private String pageCtx;
    //站点真实路径
    private String relPath;
    //上传文件保存路径
    private String savePath;
    //上传文件存放根路径

    //允许上传文件后缀map数组
    private static final Map<String, String> extMap = new HashMap<String, String>();

    //允许上传文件大小MAP数组
    private static final Map<String, Long> sizeMap = new HashMap<String, Long>();

    private String filePath = "/attached/";

    static {
        //初始化文件后缀map数组
        extMap.put("image", "gif,jpg,jpeg,png,bmp,GIF,JPG,JPEG,PNG,BMP");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,txt,zip,rar");
        //初始化文件大小map数组
        sizeMap.put("image", 10 *1024* 1024l);
        sizeMap.put("flash", 30 *1024* 1024 * 1024l);
        sizeMap.put("media", 30 *1024* 1024 * 1024l);
        sizeMap.put("file", 10 *1024* 1024 * 1024l);
    }

    private static final String urlStr = "http://183.247.179.221:9099/fileUpload";
    public static String userToken = null;
    public static String problemName = null;
    /**
     * 文件图片上传
     * @param imgFile
     * @param request
     * @param dir
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public Map<String, Object> uploadFileForKindEdit(MultipartFile imgFile, HttpServletRequest request, String dir) {
        Map<String, Object> map = new HashMap<String, Object>();
        Properties prop = new Properties();
        String fileUploadPath = null;
        String fileAcquireUrl = null;
        try {
            prop.load(KindEditorController.class.getClassLoader().getResourceAsStream("conf/config.properties"));
            fileUploadPath = prop.getProperty("uploadUrl").trim();
            fileAcquireUrl = prop.getProperty("realUrl").trim();
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "读取文件路径失败");
            return map;
        }
        //检查并创建上传文件夹
        File uploadFileDir = new File(fileUploadPath);
        if(!uploadFileDir.exists() || !uploadFileDir.isDirectory()) {
            uploadFileDir.mkdirs();
        }
        //初始化相关变量
//        ServletContext context = request.getSession().getServletContext();
//        pageCtx = context.getContextPath().concat(filePath);
//        relPath = context.getRealPath(filePath);
        relPath = fileUploadPath;
        pageCtx = fileAcquireUrl;
        fileDir = dir;
        if(null == dir || dir.isEmpty()) {
            fileDir = "file";
        }
        //检查是否有文件上传
        if(null == imgFile) {
            map.put("error", 1);
            map.put("message", "请选择上传文件.");
            return map;
        }
        //检查上传文件保存目录是否存在或可改
        if (!isExistOrRwFolder()) {
            map.put("error", 1);
            map.put("message", "上传文件保存目录不存在或\n是没有写入权限,请检查.");
            return map;
        }
        // 检查目录名称是否正确
        if (!extMap.containsKey(fileDir)) {
            map.put("error", 1);
            map.put("message", "目录名不正确,请检查.");
            return map;
        }
        //计算出文件后缀名
        fileExt = FilenameUtils.getExtension(imgFile.getOriginalFilename());
        // 检查上传文件类型
        if(!Arrays.<String>asList(extMap.get(fileDir).split(",")).contains(fileExt)){
            map.put("error", 1);
            map.put("message", "上传文件的格式被拒绝,\n只允许" + extMap.get(fileDir) + "格式的文件.");
            return map;
        }
        // 检查上传文件的大小
        long maxSize = sizeMap.get(fileDir);
        if (imgFile.getSize() > maxSize) {
            map.put("error", 1);
            String size = null;
            if(maxSize < 1024) {
                size = maxSize + "B";
            }
            if(maxSize > 1024 && maxSize < 1024 * 1024){
                size = maxSize/1024 + "KB";
            }
            if(maxSize > 1024 * 1024){
                size = maxSize/(1024 * 1024) + "MB";
            }
            map.put("message", "上传文件大小超过限制,只允\n许上传小于 " + size + " 的文件.");
            return map;
        }
        // 生成新的文件名,并按日期分类
        newSavePath();
        // 拷贝上传文件至指定存放目录
        try {
            copy(imgFile.getInputStream(), savePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 计算出文件输出路径
        int point = savePath.lastIndexOf("/") - 8;
        StringBuilder url = new StringBuilder(pageCtx);
        url.append(fileDir).append("/");
        url.append(savePath.substring(point));
        // 返回上传文件的输出路径至前端
        map.put("error", 0);
        map.put("url",url.toString());
        return map;
    }

    // 拷贝上传文件至指定存放目录
    private void copy(InputStream src, String tar) {
        // 判断源文件或目标路径是否为空
        if (null == src
                || null == tar
                || tar.isEmpty()) {
            return;
        }
        //InputStream srcIs = null;
        OutputStream tarOs = null;
        try {
            //srcIs = new FileInputStream(src);
            File tarFile = new File(tar);
            tarOs = new FileOutputStream(tarFile);
            byte[] buffer = new byte[4096];
            int n = 0;
            while (-1 != (n = src.read(buffer))) {
                tarOs.write(buffer, 0, n);
            }
        } catch (IOException e) {
            log.error("Copy File is Fali, Because "+e);
        } finally {
            try {
                if (null != src) {
                    src.close();
                }
                if (null != tarOs) {
                    tarOs.close();
                }
            } catch (IOException e) {
                log.error("Close Stream is Fail, Because "+e);
            }
        }
    }

    /**
     * @category 管理文件上传的请求以及目录管理
     * @param dir
     * @param request
     * @param path
     * @param order
     * @return
     */
    @RequestMapping("/manager")
    @ResponseBody
    public Map<String, Object> manager(String dir, HttpServletRequest request, String path, String order) {
        Map<String, Object> map = new HashMap<String, Object>();
        Properties prop = new Properties();
        String fileUploadPath = null;
        String fileAcquireUrl = null;
        try {
            prop.load(KindEditorController.class.getClassLoader().getResourceAsStream("conf/config.properties"));
            fileUploadPath = prop.getProperty("uploadUrl").trim();
            fileAcquireUrl = prop.getProperty("realUrl").trim();
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error", 1);
            map.put("message", "读取文件路径失败");
            return map;
        }
        //检查并创建上传文件夹
        File uploadFileDir = new File(fileUploadPath);
        if(!uploadFileDir.exists() || !uploadFileDir.isDirectory()) {
            uploadFileDir.mkdirs();
        }
        relPath = fileUploadPath;
        pageCtx = fileAcquireUrl;
        fileDir = dir;
        if (null == dir || dir.isEmpty()) {
            fileDir = "file";
        }
        if (!extMap.containsKey(fileDir)) {
            map.put("error", 1);
            map.put("message", "目录名不正确,请检查.");
            return map;
        }
        String tempPath = null == path ? fileDir.concat("/") : fileDir.concat("/"+path);
        String curPath = pageCtx.concat(tempPath);
        String curFileDir = relPath.concat("/"+tempPath);
        String curDir = path;
        String moveupDir = "";
        // 检查当前目录是否为根目录
        if (!"".equals(path)) {
            String str = curDir.substring(0, curDir.length() - 1);
            moveupDir = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }
        // 检查..命令
        if(path.indexOf("..") >= 0){
            map.put("error", 1);
            map.put("message", "不允许使用..命令返回上一层.");
            return map;
        }
        //最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            map.put("error", 1);
            map.put("message", "文件路径不合法.");
            return map;
        }
        // 检查当前目录
        File curPathFile = new File(curFileDir);
        if (!curPathFile.isDirectory()) {
            map.put("error", 1);
            map.put("message", "当前目录不存在.");
            return map;
        }
        //遍历目录取的文件信息
        @SuppressWarnings("rawtypes")
        List<HashMap> fileList = new ArrayList<HashMap>();
        if (curPathFile.listFiles() != null) {
            for (File file : curPathFile.listFiles()) {
                HashMap<String, Object> hash = new HashMap<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }
        // 文件排序方式
        String tempOrder = order != null ? order.toLowerCase() : "name";
        if ("size".equals(tempOrder)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(tempOrder)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        // 输出遍历后的文件信息数据
        map.put("moveup_dir_path", moveupDir);
        map.put("current_dir_path", curDir);
        map.put("current_url", curPath);
        map.put("total_count", fileList.size());
        map.put("file_list", fileList);
        return map;
    }

    //生成新的文件名，并按日期分类
    private void newSavePath() {
        StringBuilder tempPath = new StringBuilder(relPath);
        tempPath.append("/").append(fileDir).append("/");
        SimpleDateFormat folderNameFormat = new SimpleDateFormat("yyyyMMdd");
        tempPath.append(folderNameFormat.format(new Date()));
        File temp = new File(tempPath.toString());
        if(!temp.exists()) temp.mkdirs();
        SimpleDateFormat fileNameFormat = new SimpleDateFormat("yyyyMMddkkmmss_S");
        tempPath.append("/").append(fileNameFormat.format(new Date()));
        tempPath.append(".").append(fileExt);
        savePath = tempPath.toString().replaceAll("\\\\", "/");
    }

    /**
     * 判断文件上传保存的文件夹是否存在或可写
     * @return 如果存在且可写返回"true",否则返回"false"
     */
    private boolean isExistOrRwFolder() {
        if(null == relPath || relPath.isEmpty()) {
            return false;
        }
        File folder = new File(relPath);
        //文件路径不存在则创建目录
        if(!folder.exists()) {
            folder.mkdirs();
        }
        if(!folder.isDirectory())
            return false;
        if(!folder.canWrite())
            return false;
        return true;
    }


    /**
     * @category 查询服务器的文件并根据文件名称排序
     */
    @SuppressWarnings("rawtypes")
    public class NameComparator implements Comparator {

        //复写比较器
        public int compare(Object a, Object b) {
            HashMap hashA = (HashMap) a;
            HashMap hashB = (HashMap) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
            }
        }
    }

    /**
     * @category 查询服务器的文件并根据文件大小排序
     */
    @SuppressWarnings("rawtypes")
    public class SizeComparator implements Comparator {

        //复写比较器
        public int compare(Object a, Object b) {
            HashMap hashA = (HashMap) a;
            HashMap hashB = (HashMap) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                    return 1;
                } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    /**
     * @category 查询服务器的文件并根据文件类型排序
     */
    public class TypeComparator implements Comparator {

        //复写比较器
        public int compare(Object a, Object b) {
            HashMap hashA = (HashMap) a;
            HashMap hashB = (HashMap) b;
            if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
            }
        }
    }
}
