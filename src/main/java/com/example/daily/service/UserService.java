package com.example.daily.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.daily.DTO.LoginDTO;
import com.example.daily.DTO.UserDTO;
import com.example.daily.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author wushuang
 */
@Service
public interface UserService extends IService<User>{

    /**
     * 注册
     */

    public String addUser(UserDTO userDTO);

    /**
     * 登录
     */
    public String login(LoginDTO loginDTO);

    /**
     * 查询
     */
    public User getUser(String uuid);

    void updateUserInfo(String uuid, String introduction, Integer age, Integer sex);
}
