package cn.segema.learn.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot2.service.HttpLogSrervice;
import cn.segema.learn.springboot2.vo.HttpLogVO;

@RestController
@RequestMapping(value = "/clickhouse")
public class ClickhouseController {

	@Autowired
	private HttpLogSrervice httpLogSrervice;

	@PostMapping
	public ResponseEntity add(@RequestBody HttpLogVO httpLogVO) {
		httpLogSrervice.save(httpLogVO);
		return new ResponseEntity(null);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(Integer id) {
		httpLogSrervice.deleteById(id);
		return new ResponseEntity(null);
	}

	@PutMapping
	public ResponseEntity update(HttpLogVO httpLogVO) {
		httpLogSrervice.update(httpLogVO);
		return new ResponseEntity(null);
	}

	@GetMapping("/list")
	public ResponseEntity list() {
		return new ResponseEntity(null);
	}
}
