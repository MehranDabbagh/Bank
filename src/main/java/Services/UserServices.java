package Services;

import Entities.User;
import Repositories.UserRepositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServices {
    private UserRepositories userRepositories=new UserRepositories();
    private User loggedIn;
    private AccServices accServices=new AccServices();

    public UserServices() throws SQLException, ClassNotFoundException {
    }

    public boolean login(String nationalCode,String password){
      User  user= userRepositories.read(nationalCode,password);
       if(user!=null){
           loggedIn=user;
           return true;
       }else System.out.println("there is no user with this national code or the password is wrong!");
       return false;

    }
    public void create(String nationalCode,String password,String accPassword,String branchName){
        userRepositories.createUser(nationalCode,password,branchName);
        User user=new User(nationalCode,password,branchName);
        accServices.create(user,accPassword);
    }

}
