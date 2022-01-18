package Services;

import Repositories.BankRepositories;
import Repositories.ManagerRepositories;
import Repositories.NormalEmployeeRepositories;

public class AdminService {
    private BankRepositories bankRepositories=new BankRepositories();
    private ManagerRepositories managerRepositories=new ManagerRepositories();
    private NormalEmployeeRepositories normalEmployeeRepositories=new NormalEmployeeRepositories();
    public void createBranch(String branchName){
           bankRepositories.create(branchName);
    }
    public void createManageer(String nationalCode,String branchName){
        managerRepositories.create(nationalCode,branchName);
    }
    public void createNormalEmployee(String nationalCode,String branchName){
        normalEmployeeRepositories.create(nationalCode,branchName);
    }
}
