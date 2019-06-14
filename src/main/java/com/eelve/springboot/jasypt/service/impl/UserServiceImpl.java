package com.eelve.springboot.jasypt.service.impl;

import com.eelve.springboot.jasypt.domain.UserDetail;
import com.eelve.springboot.jasypt.model.User;
import com.eelve.springboot.jasypt.repository.UserRepository;
import com.eelve.springboot.jasypt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/6/14 10:48
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findList(Integer page, Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<User> users=userRepository.findList(pageable);
        return users;
    }

    @Override
    public User findByUserName(UserDetail user) {
        User u= userRepository.findByUserName(user.getUserName());
        return u;
    }

    @Override
    public void saveUser(UserDetail user) {
        User newUser=new User();
        BeanUtils.copyProperties(user,newUser);
        newUser.setRegTime(new Date());
        userRepository.save(newUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
