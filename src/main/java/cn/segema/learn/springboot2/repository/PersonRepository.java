package cn.segema.learn.springboot2.repository;

import java.math.BigInteger;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import cn.segema.learn.springboot2.domain.Person;

@CacheConfig(cacheNames = "personRepository")
@Repository
public interface PersonRepository extends JpaRepository<Person, BigInteger>,JpaSpecificationExecutor<Person> {

   
    
}
