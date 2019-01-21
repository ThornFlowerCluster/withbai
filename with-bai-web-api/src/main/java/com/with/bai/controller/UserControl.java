package com.with.bai.controller;


import com.with.bai.domain.User;
import com.with.bai.service.UserService;
import com.with.bai.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "user")
public class UserControl {

    @Autowired
    private UserService userService;

    @ModelAttribute
    public User getUser(long id){
        User user = userService.getUserByid(id);
        if(user==null){
            return new User();
        }else{
            return user;
        }
    }

    /*
    注册
     */
    @RequestMapping(value = "adduser")
    public BaseResult addUser(User user){
        boolean flag=userService.addUser(user);
        if(flag){
            return BaseResult.success();
        }else{
            return BaseResult.fail();
        }

    }
}
