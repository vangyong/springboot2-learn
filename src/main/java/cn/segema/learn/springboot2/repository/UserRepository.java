package cn.segema.learn.springboot2.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.vo.UserVO;

@CacheConfig(cacheNames = "userRepository")
@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>,JpaSpecificationExecutor<User> {

    @Query(value = "SELECT a.* FROM tb_user a", nativeQuery = true)
    List<User> getAll();
    
    @Query(value = "select * from tb_user where user_id =?1",nativeQuery = true)
    Optional<User> findById(BigInteger userId);
    
    @Transactional
    @Modifying
    @CacheEvict(value = "getAllUsers",keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "insert into tb_user(user_id,user_name,password) values(:#{#user.userId},:#{#user.userName},:#{#user.password})",
           nativeQuery = true)
    void create(@Param("user") UserVO user);
    
    @Transactional
    @Modifying
    @Query(value = "insert into tb_user(user_id,user_name,password) values(?1,?2,?3)",nativeQuery = true)
    int createByParam(BigInteger userId,String userName,String password);
    
    @Transactional
    @Modifying
    //@CacheEvict(value = {"findById"},keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "update tb_user set user_name=:#{#user.userName} where user_id=:#{#user.userId}",nativeQuery = true)
    void update(@Param("user") UserVO user);
    
    //@CacheEvict(value = {"getAllUsers",},keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "delete from tb_user where user_id=?1",nativeQuery = true)
    void deleteById(BigInteger userId);
    
    @Query(value = "SELECT * FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) and if(:#{#user.gender}!='',gender = :#{#user.gender},1=1) ORDER BY ?#{#pageable}",
               countQuery = "SELECT count(*) FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) and if(:#{#user.gender}!='',gender = :#{#user.gender},1=1)",
               nativeQuery = true)
    public Page<User> findByPage(@Param("user") UserVO user, Pageable pageable);
}
