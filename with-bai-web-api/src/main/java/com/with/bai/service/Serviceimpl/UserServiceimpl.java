package com.with.bai.service.Serviceimpl;

import com.with.bai.dao.UserDao;
import com.with.bai.domain.User;
import com.with.bai.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    private UserDao dao;


    @Override
    public User getUserByid(long id) {
        return dao.getUserByid(id);
    }

    @Override
    public User getUserByemail(String email) {
        User user=dao.getuserByemail(email);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        int i=dao.addUser(user);
        if(i==0){
            return false;
        }else{
            return true;
        }
    }


}
