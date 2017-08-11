package com.xuezw.template.sys.auth;

import com.xuezw.template.sys.domain.User;

public interface AuthService {
    boolean register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}