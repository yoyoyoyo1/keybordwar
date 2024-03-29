package com.oracle.demo.config;

import com.oracle.demo.config.intercepors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    // 这个方法是用来配置静态资源的，比如html，js，css，等等
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                  .addResourceLocations("classpath:/static/");
    }

    // 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/","/userlogin", "/userreg",
                  "/user","/js/**","/css/**","/images/**","/fonts/**","/lib/**","/toadminlogin","/gethello","/admin/**",
                  "/adminlogin","/gethello1","/getwelcome","/adminlogout","/toadminadduser","/adminadduser",
                  "/tohello1","/adminfinduserbynname","/amdinfinduserbyemail","/adminfinduserbyphone","/admindeluserbyid",
                  "/delbhuser","/getnkname","/toadminedituser","/adminedituser","/adminedithimage","/toadmineditpass",
                  "/admineditpass","/toadminuserlistbypage","/admindeluser","/admintouserdt","/delbhshare","/adminshowuserdt","/toallshare","/toadminadddialog","/adminadddialog","/admindialoglist","/delbhdialog","/admindeldia","/toadmineditdia","/admineditdialog","/admindialogon","/admindialogoff","/toshowshare","/admindelshare","/touser","/userimage/**","/dialogimage/**","/images/**");

    }

}
