package cn.segema.learn.springboot2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.segema.learn.springboot2.domain.MovieNode;

@Repository
public interface MovieNodeRepository extends CrudRepository<MovieNode, Long> {

    MovieNode findByTitle(String title);

}
