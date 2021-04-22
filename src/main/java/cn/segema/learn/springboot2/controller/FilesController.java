package cn.segema.learn.springboot2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot2.utils.FileUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/files")
public class FilesController {
	private final static Logger logger = LoggerFactory.getLogger(FilesController.class);

	@ApiOperation(value = "下载文件", notes = "下载文件")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "文件id", required = true, paramType = "path") })
	@RequestMapping(value = "/download/{id}", method = RequestMethod.GET)
	public void download(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) {
		String storeName = "/opt/uploads/271569718778880/1001.docx";
		String fileName = "1001.docx";
		String contentType = "application/x-download";
		try {
			FileUtil.download(request, response, storeName, contentType, fileName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
