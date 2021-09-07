package com.work.information.service;

import com.work.information.entity.User;
import com.work.information.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Author: miki
 * @Date: 2021/07/24/9:10
 * @Description:
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    //查询所有
    public List<User> selectAll(){
        return this.userMapper.selectAll();
    }
    //根据id查询
    public User findById(int id){
        return this.userMapper.selectByPrimaryKey(id);
    }
   //根据ID删除
    public void deleteById(int id) {
        this.userMapper.deleteByPrimaryKey(id);
    }
    //更新操作
    public void update(User user) {
        User userUpdate = new User();
        userUpdate.setId(user.getId());
        System.out.println("user.getId()"+user.getId());
        userUpdate.setSex(user.getSex());
        userUpdate.setYear(user.getYear());
        userUpdate.setTotalMileage(user.getTotalMileage());
        userUpdate.setTotalTime(user.getTotalTime());
        this.userMapper.updateByPrimaryKey(userUpdate);

    }

    //增加数据记录
    public void addUser(User user){
        User addUser = new User();
           /* addUser.setId(user.getId());
            System.out.println("user.getId()"+user.getId());*/
            addUser.setSex(user.getSex());
            addUser.setYear(user.getYear());
            addUser.setTotalMileage(user.getTotalMileage());
            addUser.setTotalTime(user.getTotalTime());
            this.userMapper.insert(addUser);

    }


    //根据年份查询
    public List<User> queryByYear(Integer year1,Integer year2) {
        // 初始化example对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("year",year);
        criteria.orGreaterThanOrEqualTo("year",year1)
                .andLessThanOrEqualTo("year",year2);
        return this.userMapper.selectByExample(example);
    }

    //按飞行里程查询
    public List<User> queryByMile(Integer mile1,Integer mile2) {
        // 初始化example对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("totalMileage",mile);
        criteria.orGreaterThanOrEqualTo("totalMileage",mile1)
                .andLessThanOrEqualTo("totalMileage",mile2);
        return this.userMapper.selectByExample(example);
    }

    //按飞行时间查询
    public List<User> queryByTime(Integer time1,Integer time2) {
        // 初始化example对象
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        //criteria.andEqualTo("totalTime",time);
        criteria.orGreaterThanOrEqualTo("totalTime",time1)
                .andLessThanOrEqualTo("totalTime",time2);
        return this.userMapper.selectByExample(example);
    }

    //增加数据记录
    public void insertUser(User user){
        this.userMapper.insert(user);
    }



}
