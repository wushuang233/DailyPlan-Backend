package com.example.daily.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author wushuang
 */
@SpringBootConfiguration
@EnableWebMvc
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器，打开注解式鉴权功能
     * 如果在高版本 SpringBoot (≥2.6.x) 下注册拦截器失效，则需要额外添加 @EnableWebMvc 注解才可以使用
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册路由拦截器，自定义认证规则
        registry.addInterceptor(new SaInterceptor(handler -> {
                    // 登录认证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//                    SaRouter.match("/**", "/user/doLogin", r -> StpUtil.checkLogin());
                    // 角色认证 -- 拦截以 admin 开头的路由，必须具备 admin 角色或者 super-admin 角色才可以通过认证
//                    SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("admin", "super-admin"));
                    // 权限认证 -- 不同模块认证不同权限
//                    SaRouter.match("/user/**", r -> StpUtil.checkRole("user"));
                    SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                    // 甚至你可以随意的写一个打印语句
//                    SaRouter.match("/**", r -> System.out.println("--------权限认证成功-------"));
                }).isAnnotation(true))
                //拦截所有接口
                .addPathPatterns("/admin/**")
                //不拦截login登录接口
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/islogin")
                .excludePathPatterns("/admin/logout");

    }

}
