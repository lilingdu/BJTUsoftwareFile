package com.work.information.mapper;

import com.work.information.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @Author: miki
 * @Date: 2021/07/24/9:08
 * @Description:
 */

@Mapper
@Component
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User> {
}
