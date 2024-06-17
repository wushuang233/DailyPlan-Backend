package com.example.daily.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wushuang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @NotBlank(message = "账号不允许为空")
    @Size(max = 50, message = "账号长度不能超过50个字符")
    private String username;

    @NotBlank(message = "密码不允许为空")
    private String password;

    @NotBlank(message = "邮箱不允许为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @NotNull(message = "性别不允许为空")
    private Integer sex;

    @NotNull(message = "年龄不允许为空")
    private Integer age;

}
