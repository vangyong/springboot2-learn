package cn.segema.learn.springboot2.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Mono;

@Api(tags = "flux测试")
@RestController
@RequestMapping(value = "/flux")
public class FluxController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "根据id获取用户", notes = "根据id获取用户")
    @GetMapping("/{userId}")
    public Mono<Object> findById(@PathVariable BigInteger userId) {
    	return Mono.create(cityMonoSink -> cityMonoSink.success(userRepository.findById(userId)));
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping
    public Mono<Object> add(@RequestBody User user) {
        user.setUserId("123");
        return Mono.create(cityMonoSink -> cityMonoSink.success(userRepository.save(user)));
    }
}
