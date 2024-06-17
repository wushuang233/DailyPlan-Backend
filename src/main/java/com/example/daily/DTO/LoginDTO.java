package com.example.daily.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "账号不允许为空")
    @Size(max = 50, message = "账号长度不能超过50个字符")
    private String username;

    @NotBlank(message = "密码不允许为空")
    private String password;

}
