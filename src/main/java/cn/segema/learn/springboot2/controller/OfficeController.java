package cn.segema.learn.springboot2.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import io.swagger.annotations.ApiOperation;

@RestController
public class OfficeController {

	@ApiOperation(value = "文档保存")
	@RequestMapping(value = "/docx/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public void saveWord(HttpServletRequest request, HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			String body = "";

			try {
				Scanner scanner = new Scanner(request.getInputStream());
				scanner.useDelimiter("\\A");
				body = scanner.hasNext() ? scanner.next() : "";
				scanner.close();
			} catch (Exception ex) {
				writer.write("get request.getInputStream error:" + ex.getMessage());
				return;
			}

			if (body.isEmpty()) {
				writer.write("empty request.getInputStream");
				return;
			}

			JSONObject jsonObj = JSON.parseObject(body);

			int status = (Integer) jsonObj.get("status");

			int saved = 0;
			if (status == 2 || status == 3)// MustSave, Corrupted
			{
				String downloadUri = (String) jsonObj.get("url");

				try {
					URL url = new URL(downloadUri);
					java.net.HttpURLConnection connection = (java.net.HttpURLConnection) url.openConnection();
					InputStream stream = connection.getInputStream();
					if (stream == null) {
						throw new Exception("Stream is null");
					}
					// 根据key C8D7FB890BAC496FB0D27B163EDB88BDAA 获取文件目录地址 
					// 从请求中获取要覆盖的文件参数定义"path"
					//String path = request.getParameter("path");
					String path = "/opt/uploads/271569718778880/1001.docx";

					File savedFile = new File(path);
					try (FileOutputStream out = new FileOutputStream(savedFile)) {
						int read;
						final byte[] bytes = new byte[1024];
						while ((read = stream.read(bytes)) != -1) {
							out.write(bytes, 0, read);
						}
						out.flush();
					}
					connection.disconnect();
				} catch (Exception ex) {
					saved = 1;
					ex.printStackTrace();
				}
			}
			System.out.print("编辑完成--------------11111");
			writer.write("{\"error\":" + saved + "}");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
