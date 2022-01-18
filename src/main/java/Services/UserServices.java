package Services;

import Entities.User;
import Repositories.UserRepositories;

import java.util.ArrayList;
import java.util.List;

public class UserServices {
    private UserRepositories userRepositories=new UserRepositories();
    private User loggedIn;
    public void login(String nationalCode,String password){
      User  user= userRepositories.read(nationalCode,password);
       if(user!=null){
           loggedIn=user;
       }else System.out.println("there is no user with this national code or the password is wrong!");

    }
    public void create(String nationalCode,String password){
        userRepositories.createUser(nationalCode,password);
    }
}
