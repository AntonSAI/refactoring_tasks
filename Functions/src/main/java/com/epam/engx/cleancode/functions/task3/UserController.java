package com.epam.engx.cleancode.functions.task3;

import com.epam.engx.cleancode.functions.task3.thirdpartyjar.Controller;
import com.epam.engx.cleancode.functions.task3.thirdpartyjar.User;

public abstract class UserController implements Controller {

    private UserAuthenticator userAuthenticator;

    public void authenticateUser(String userName, String password) {
        if (createUser(userName, password) == null) {
            generateFailLoginResponse();
        } else {
            generateSuccessLoginResponse(userName);
        }
    }

    private User createUser(String userName, String password) {
        User user = userAuthenticator.login(userName, password);
        return user;
    }
}