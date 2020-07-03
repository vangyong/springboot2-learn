package cn.segema.learn.springboot2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cn.segema.learn.springboot2.domain.PersonNode;

@Repository
public interface PersonNodeRepository extends CrudRepository<PersonNode, Long> {

    PersonNode findByName(String name);

}
