package com.tu.usercontrol.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.source.tree.ReturnTree;
import com.tu.usercontrol.model.domain.User;
import com.tu.usercontrol.model.domain.request.UserLoginRequest;
import com.tu.usercontrol.model.domain.request.UserRegisterRequest;
import com.tu.usercontrol.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.tu.usercontrol.contant.UserConstant.ADMIN_ROLE;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest){
        if (userRegisterRequest == null){
            return null;
        }

        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkpassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAllBlank(userAccount, userPassword, checkpassword)){
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkpassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request){
        if (userLoginRequest == null){
            return null;
        }

        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAllBlank(userAccount, userPassword)){
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }

    @GetMapping("/search")
    public List<User> searchUsers(String username, HttpServletRequest request){
        if (!isAdmin(request)){
            return new ArrayList<>();
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNoneBlank(username)){
            queryWrapper.like("username", username);
        }
        return userService.list(queryWrapper);
    }

    @PostMapping("/delete")
    public boolean deleteUser(@RequestBody long id, HttpServletRequest request){
        if (!isAdmin(request)){
            return false;
        }

        if (id <= 0){
            return false;
        }
        return userService.removeById(id);
    }
    private boolean isAdmin(HttpServletRequest request){
        //仅限管理员查询
        Object userObj = request.getSession().getAttribute(UserService.USER_LOGIN_STATE);
        User user = (User) userObj;
        if (user == null || user.getRole() != ADMIN_ROLE){
            return false;
        }
        return true;
    }


}
