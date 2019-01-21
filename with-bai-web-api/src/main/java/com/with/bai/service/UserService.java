package com.with.bai.service;

import com.with.bai.domain.User;

public interface UserService {

    public User getUserByid(long id);

    public User getUserByemail(String email);

    boolean addUser(User user);
}
