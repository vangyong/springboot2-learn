package cn.segema.learn.springboot2.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot2.async.AsyncTask;

@RestController
@RequestMapping(value = "/async")
public class AsyncController {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AsyncTask asyncTask;

	@GetMapping("/{id}")
	public Map findById(@PathVariable Long id) throws Exception{
		for (int i = 0; i < 100; i++) {
			asyncTask.doTask1(i);
		}
		logger.info("All tasks finished.");

		Map map = new HashMap<String, String>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		return map;
	}

}
