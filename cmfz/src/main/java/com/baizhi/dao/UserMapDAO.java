package com.baizhi.dao;

import com.baizhi.entity.UserMap;

import java.util.List;

public interface UserMapDAO {
    List<UserMap> queryMan();
    List<UserMap> queryWomen();
}
