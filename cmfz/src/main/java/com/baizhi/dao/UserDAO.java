package com.baizhi.dao;


import com.baizhi.entity.User;
import com.baizhi.entity.UserStatistical;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserDAO {
    User queryUserByAccount(String account);
    User queryUserById(String id);
    List<User> queryAll(@Param("start")int start,@Param("rows")int rows);
    void update(User user);
    void regist(User user);
    void freeze(String id);
    void unFreeze(String id);
    UserStatistical queryActive(Date date);
    List<User> queryAllUsers();
    List<User> queryByRandom(String uid);
    void modify(User user);
}
