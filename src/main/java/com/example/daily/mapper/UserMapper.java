package com.example.daily.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.daily.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author wushuang
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE tb_user SET introduction = #{introduction}, age = #{age}, sex = #{sex} WHERE uuid = #{uuid}")
    void updateUserInfoByUuid(@Param("uuid") String uuid,
                              @Param("introduction") String introduction,
                              @Param("age") Integer age,
                              @Param("sex") Integer sex);
}