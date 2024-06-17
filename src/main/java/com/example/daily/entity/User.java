package com.example.daily.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;

/**
 * @author wushuang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tb_user")
public class User {
    @Serial
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 用户昵称
     */
    @TableField(value = "username")
    private String username;
    /**
     * 用户密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 用户性别
     */
    @TableField(value = "sex")
    private Integer sex;
    /**
     * 用户年龄
     */
    @TableField(value = "age")
    private Integer age;
    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;
    /**
     * 个人签名
     */
    @TableField(value = "introduction")
    private String introduction;

    @TableField(value = "uuid")
    private String uuid;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
