//package cn.segema.cloud.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import cn.segema.cloud.common.utils.IdGeneratorUtil;
//import cn.segema.cloud.demo.domain.DemoUser;
//import cn.segema.cloud.demo.repository.DemoUserRepository;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import reactor.core.publisher.Mono;
//
//@Api(tags = "flux测试")
//@RestController
//@RequestMapping(value = "/demo/flux")
//public class DemoFluxController {
//
//    @Autowired
//    private DemoUserRepository demoUserRepository;
//
//    @ApiOperation(value = "根据id获取用户", notes = "根据id获取用户")
//    @GetMapping("/{userId}")
//    public Mono<Object> findById(@PathVariable String userId) {
//    	return Mono.create(cityMonoSink -> cityMonoSink.success(demoUserRepository.findById(userId)));
//    }
//
//    @ApiOperation(value = "新增用户", notes = "新增用户")
//    @PostMapping
//    public Mono<Object> add(@RequestBody DemoUser user) {
//        user.setUserId(IdGeneratorUtil.generateSnowFlakeId());
//        return Mono.create(cityMonoSink -> cityMonoSink.success(demoUserRepository.save(user)));
//    }
//}
