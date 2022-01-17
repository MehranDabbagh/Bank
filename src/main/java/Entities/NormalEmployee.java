package Entities;

public class NormalEmployee extends Employee{
    private Manager manager;
    public NormalEmployee(String nationalCode,JobType jobType) {
        super(nationalCode,jobType);
    }
    public NormalEmployee(String nationalCode,JobType jobType,Manager manager) {
        super(nationalCode,jobType);
        this.manager=manager;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
