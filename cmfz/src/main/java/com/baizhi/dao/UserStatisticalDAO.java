package com.baizhi.dao;


import com.baizhi.entity.User;
import com.baizhi.entity.UserStatistical;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserStatisticalDAO {
    int queryActive(@Param("date") Date date,@Param("week") int week);
}
