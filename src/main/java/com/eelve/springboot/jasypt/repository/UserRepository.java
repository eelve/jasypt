package com.eelve.springboot.jasypt.repository;

import com.eelve.springboot.jasypt.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName UserRepository
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/6/14 10:49
 * @Version 1.0
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u")
    Page<User> findList(Pageable pageable);
    User findById(long id);
    User findByUserName(String userName);
    void deleteById(Long id);
}
