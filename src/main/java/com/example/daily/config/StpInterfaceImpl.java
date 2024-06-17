package com.example.daily.config;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.example.daily.entity.User;
import com.example.daily.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 */
@Component    // 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {

    @Resource
    UserMapper userMapper;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add("*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */

    //添加某些角色可以访问的
    @Override
    public List<String> getRoleList(Object o, String loginType) {
        //o属性就是刚刚绑定的id，通过这个id去数据库查询权限
        String uuid = (String) o;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid",uuid);

        User user = userMapper.selectOne(queryWrapper);
        String role = "user";
        List<String> list = new ArrayList<String>();
        //“*”权限表示什么都可以访问
        list.add(role);
        return list;
    }
}
