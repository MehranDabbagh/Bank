package Services;

import Repositories.BankRepositories;
import Repositories.ManagerRepositories;
import Repositories.NormalEmployeeRepositories;

import java.sql.SQLException;

public class AdminService {
    private BankRepositories bankRepositories=new BankRepositories();
    private ManagerRepositories managerRepositories=new ManagerRepositories();
    private NormalEmployeeRepositories normalEmployeeRepositories=new NormalEmployeeRepositories();

    public AdminService() throws SQLException, ClassNotFoundException {
    }

    public void createBranch(String branchName){
           bankRepositories.create(branchName);
    }
    public void createManager(String nationalCode,String branchName){
        managerRepositories.create(nationalCode,branchName);
    }
    public void createNormalEmployee(String nationalCode,String branchName){
        normalEmployeeRepositories.create(nationalCode,branchName);
    }
}
