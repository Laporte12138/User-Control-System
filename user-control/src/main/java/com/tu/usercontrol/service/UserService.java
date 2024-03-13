package com.tu.usercontrol.service;

import com.tu.usercontrol.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author examp
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-03-12 18:28:04
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    long userRegister(String userAccount, String userPassword, String checkPassword);

    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    User login(String userAccount, String userPassword, HttpServletRequest request);
}
