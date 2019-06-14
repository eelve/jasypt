package com.eelve.springboot.jasypt.service;

import com.eelve.springboot.jasypt.domain.UserDetail;
import com.eelve.springboot.jasypt.model.User;
import org.springframework.data.domain.Page;

/**
 * @author zhao.zhilue
 * @Description: user服务层
 * @date 2019/6/1410:46
 */
public interface UserService {
    Page<User> findList(Integer page, Integer size);
    User findByUserName(UserDetail user);
    void saveUser(UserDetail user);
    void deleteById(Long id);
}
