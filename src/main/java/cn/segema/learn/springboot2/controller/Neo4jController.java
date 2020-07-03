package cn.segema.learn.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot2.domain.MovieNode;
import cn.segema.learn.springboot2.domain.PersonNode;
import cn.segema.learn.springboot2.repository.MovieNodeRepository;
import cn.segema.learn.springboot2.repository.PersonNodeRepository;

/**
 * @description 图数据库neo4j
 * @author wangyong
 * @createDate 2020/07/03
 */

@RestController
@RequestMapping(value = "/neo4j")
public class Neo4jController {

    @Autowired
    private MovieNodeRepository movieNodeRepository;
    
    @Autowired
    private PersonNodeRepository personNodeRepository;

    @PostMapping("/movie/node")
    @ResponseBody
    public ResponseEntity saveMovieNode() {
        MovieNode m1 = new MovieNode("无问西东", "2018");
        MovieNode m2 = new MovieNode("罗曼蒂克消亡史", "2016");
        movieNodeRepository.save(m1);
        movieNodeRepository.save(m2);
        return new ResponseEntity("sucess", HttpStatus.OK);
    }

    @PostMapping("/person/node")
    @ResponseBody
    public ResponseEntity savePersonNode() {
        PersonNode p1 = new PersonNode("章子怡", "1979");
        PersonNode p2 = new PersonNode("李芳芳", "1976");
        PersonNode p3 = new PersonNode("程耳", "1970");
        MovieNode m1 = movieNodeRepository.findByTitle("罗曼蒂克消亡史");
        MovieNode m2 = movieNodeRepository.findByTitle("无问西东");   
        if (m1!=null) {
            p1.addActor(m1);
            p3.addDirector(m1);
        }
        if (m2!=null) {
            p1.addActor(m2);
            p2.addDirector(m2);
        }
        personNodeRepository.save(p1);
        personNodeRepository.save(p2);
        personNodeRepository.save(p3);
        
        return new ResponseEntity("sucess", HttpStatus.OK);
    }
    
    @GetMapping("/person/name")
    @ResponseBody
    public ResponseEntity getPersonByName(String name) {
        PersonNode person = personNodeRepository.findByName(name);
        return new ResponseEntity(person, HttpStatus.OK);
    }
    
    @GetMapping("/movie/title")
    @ResponseBody
    public ResponseEntity getMovieByTitle(String title) {
        MovieNode movie = movieNodeRepository.findByTitle(title);
        return new ResponseEntity(movie, HttpStatus.OK);
    }
    
}
