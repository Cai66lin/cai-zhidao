package com.caizhidao.service;


import com.caizhidao.dto.UserLoginDTO;
import com.caizhidao.entity.User;

public interface UserService {

    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
