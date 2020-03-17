//package cn.segema.learn.springboot2.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.RestTemplate;
//
//@Controller
//@RequestMapping(value = "/hystrix")
//public class HystrixController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @HystrixCommand(fallbackMethod = "findByIdFallback")
//    @GetMapping("/{userId}")
//    @ResponseBody
//    public DemoUserVO findById(@PathVariable String userId) {
//        DemoUserVO demoUserVO =
//            restTemplate.getForObject("http://microservice-demo/demo/user/" + userId, DemoUserVO.class);
//        return demoUserVO;
//    }
//
//    public DemoUserVO findByIdFallback(String userId) {
//        DemoUserVO demoUserVO = new DemoUserVO();
//        demoUserVO.setUserId("1L");
//        demoUserVO.setNickName("测试超时");
//        return demoUserVO;
//    }
//
//}
