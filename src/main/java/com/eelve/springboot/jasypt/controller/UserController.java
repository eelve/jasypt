package com.eelve.springboot.jasypt.controller;

import com.eelve.springboot.jasypt.domain.UserDetail;
import com.eelve.springboot.jasypt.model.User;
import com.eelve.springboot.jasypt.repository.UserRepository;
import com.eelve.springboot.jasypt.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @ClassName UserController
 * @Description TDO
 * @Author zhao.zhilue
 * @Date 2019/6/14 10:46
 * @Version 1.0
 **/
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model,@RequestParam(value = "page", defaultValue = "0") Integer page,
                       @RequestParam(value = "size", defaultValue = "6") Integer size) {
        Page<User> users=userService.findList(page,size);
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {
        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(@Valid UserDetail userDetail, BindingResult result, Model model) {
        String errorMsg="";
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg=errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            model.addAttribute("errorMsg",errorMsg);
            return "user/userAdd";
        }
        User u= userService.findByUserName(userDetail);
        if(u!=null){
            model.addAttribute("errorMsg","用户已存在!");
            return "user/userAdd";
        }
        userService.saveUser(userDetail);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model, Long id) {
        User user=userRepository.findById((long)id);
        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(@Valid UserDetail userDetail, BindingResult result, Model model) {
        String errorMsg="";
        if(result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            for (ObjectError error : list) {
                errorMsg=errorMsg + error.getCode() + "-" + error.getDefaultMessage() +";";
            }
            model.addAttribute("errorMsg",errorMsg);
            model.addAttribute("user", userDetail);
            return "user/userEdit";
        }
        userService.saveUser(userDetail);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String delete(Long id) {
        userService.deleteById(id);
        return "redirect:/list";
    }
}
