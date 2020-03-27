//package cn.segema.learn.springboot2.error;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice(basePackages="cn.segema.learn.springboot2.controller")
//public class GlobalExceptionHandler {
//
//	@ExceptionHandler(RuntimeException.class)
//	@ResponseBody
//	public Map<String,Object> errorResult(){
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("errotcode", 500);
//		map.put("errotmsg", "系统错误");
//		return map;
//	}
//}
