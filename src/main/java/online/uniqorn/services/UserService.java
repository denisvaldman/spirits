package online.uniqorn.services;

import online.uniqorn.models.User;
import online.uniqorn.repositories.UserRepositoryFB;
import online.uniqorn.repositories.UserRepositoryLocal;
import org.apache.http.auth.AuthenticationException;

import java.util.concurrent.ExecutionException;

public class UserService {

    UserRepositoryFB userRepositoryFB;
    UserRepositoryLocal userRepositoryLocal;

    public UserService() {
        userRepositoryFB = new UserRepositoryFB();
        userRepositoryLocal = new UserRepositoryLocal();
    }

    public void logIn(String username, String password) throws AuthenticationException, ExecutionException, InterruptedException {
        User loggedInUser = userRepositoryFB.loginUser(username,password);
        userRepositoryLocal.saveUser(loggedInUser);
    }

    public boolean checkUser(){
        return  userRepositoryLocal.userExist();
    }
}
