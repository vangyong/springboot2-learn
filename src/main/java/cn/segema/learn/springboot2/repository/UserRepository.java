package cn.segema.learn.springboot2.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
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

import cn.segema.learn.springboot2.domain.Person;
import cn.segema.learn.springboot2.domain.User;
import cn.segema.learn.springboot2.vo.UserPersonVO;
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
    public Page<Map> findByPage(@Param("user") UserVO user, Pageable pageable);
    
//nativeQuery 结果集用map装   
    @Query(value = "SELECT u.user_id as userId,u.user_name as userName,u.nick_name as nickName,p.person_id as personId,p.person_name as personName "
    		+ " FROM tb_user u,tb_person p,tb_user_person up "
    		+ " WHERE u.user_id = up.user_id AND p.person_id = up.person_id"
    		+ "  AND if(:#{#user.userName}!='',u.user_name = :#{#user.userName},1=1)",
            nativeQuery = true)
    public Page<Map> findUserPersonByPage(Pageable pageable,@Param("user") UserVO user);
    
//jpql 结果集用VO装      
//    @Query(value = "SELECT new cn.segema.learn.springboot2.vo.UserPersonVO(u.userId as userId,u.userName as userName,u.nickName as nickName,p.personId as personId,p.personName as personName)  "
//    		+ " FROM User u, Person p,UserPerson up "
//    		+ " WHERE u.userId = up.userId AND p.personId = up.personId "
//    		+ " AND (:#{#user.userName} IS NULL OR u.userName = :#{#user.userName})"
//            )
//    public Page<UserPersonVO> findUserPersonByPage(Pageable pageable,@Param("user") UserVO user);
    
//jpql 结果集用map装    
//    @Query(value = "SELECT u.userId as userId,u.userName as userName,u.nickName as nickName,p.personId as personId,p.personName as personName  "
//    		+ " FROM User u, Person p,UserPerson up "
//    		+ " WHERE u.userId = up.userId AND p.personId = up.personId "
//    		+ " AND (:#{#user.userName} IS NULL OR u.userName = :#{#user.userName})"
//            )
//    public Page<Map> findUserPersonByPage(Pageable pageable,@Param("user") UserVO user);
    
    
    @Query(value = "SELECT u.* FROM tb_user u WHERE if(:#{#user.userName}!='',u.user_name = :#{#user.userName},1=1)"
    		+ "   ORDER BY u.user_id ",
    		nativeQuery = true)
    public List<Map> findUserPersonList(@Param("user") UserVO user);
    
}
