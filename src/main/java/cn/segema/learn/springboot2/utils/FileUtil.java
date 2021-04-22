package cn.segema.learn.springboot2.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);
	/**
	 * @description 上传文件
	 * @param file
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }
	
	/**
	 * @description 下载文件
	 * @param request
	 * @param response
	 * @param storeName
	 * @param contentType
	 * @param realName
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request,
			HttpServletResponse response, String storeName, String contentType,
			String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		long fileLength = new File(storeName).length();
		response.setContentType(contentType);
		String filename=realName;
		String header = request.getHeader("User-Agent").toUpperCase();
		if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {  
		    filename = URLEncoder.encode(filename, "UTF-8"); 
		    filename = filename.replace("+", "%20"); //IE下载文件名空格变+号问题
		} else { 
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");  
		}
		response.setHeader("Content-disposition", "attachment; filename="+ filename);
		response.setHeader("Content-Length", String.valueOf(fileLength));

		bis = new BufferedInputStream(new FileInputStream(storeName));
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		bos.close();
	}
	
	/**
     * @description 视频剪辑
     * @param file 源文件
     * @param outFilePath 输出文件路径
     * @param startSeconds 开始时间
     * @param endSeconds 结束时间
     */
	public static void linuxEditor(File file ,String outFilePath,Integer startSeconds,Integer endSeconds) {
	    File targetFile = new File(outFilePath);  
        if(!targetFile.exists()){   
            targetFile.mkdirs();    
        }       
	    String command = "ffmpeg -ss 00:00:10.0 -i "+file.getAbsolutePath()+" -to 00:02:00 -c copy "+outFilePath+" -y";
	    try {
	        Runtime rt = Runtime.getRuntime();
	        Process proc = rt.exec(command);
	        InputStream stderr = proc.getErrorStream();
	        InputStreamReader isr = new InputStreamReader(stderr);
	        BufferedReader br = new BufferedReader(isr);
	        String line = null;
	        while ((line = br.readLine()) != null){
	            logger.info(line);
	        }
	        int exitVal = proc.waitFor();
	        logger.info("Process exitValue: " + exitVal);
	    } catch (IOException e) {
	        logger.info(e.getMessage());
	    } catch (InterruptedException e) {
	        logger.info(e.getMessage());
	    }
	}
	
    /**
     * @description 视频剪辑
     * @param file 源文件
     * @param outFilePath 输出文件路径
     * @param startSeconds 开始时间
     * @param endSeconds 结束时间
     */
    public static void windowsEditor(File file ,String outFilePath,Integer startSeconds,Integer endSeconds){
        List<String> convert = new ArrayList<String>();
        convert.add("/tools/ffmpeg.exe"); // 添加转换工具路径
        convert.add("-ss");//起始时间
        convert.add("00:00:10.0");
        convert.add("-i"); // 添加参数＂-i＂，该参数指定要转换的文件
        convert.add(file.getAbsolutePath()); // 添加要转换格式的视频文件的路径
        convert.add("-to");//结束时间
        convert.add("00:02:00");//
        convert.add("-c");//操作方式
        convert.add("copy");
        convert.add(outFilePath);
        convert.add("-y"); // 添加参数＂-y＂，该参数指定将覆盖已存在的文件
 
        ProcessBuilder builder = new ProcessBuilder();
        try {
            builder.command(convert);
            builder.start();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
    }
    
}
