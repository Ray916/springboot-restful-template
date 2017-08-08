package com.xuezw.template.auth;

import com.xuezw.template.domain.User;

public interface AuthService {
    boolean register(User userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);
}