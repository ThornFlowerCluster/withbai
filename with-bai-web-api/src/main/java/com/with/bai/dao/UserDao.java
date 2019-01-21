package com.with.bai.dao;

import com.with.bai.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    User getUserByid(long id);

    User getuserByemail(String email);

    int addUser(User user);
}
