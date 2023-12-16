package bigproject_pro192_taskmanagement.DTO;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Staff {

    private int bonusSalary;
    private List<Staff> staffs;

    public Manager() {
        this.staffs = new ArrayList<>();
    }

    public Manager(int id, String name, String gender, int basicSalary, int bonusSalary) {
        super(id, name, gender, basicSalary);
        this.bonusSalary = bonusSalary;
        this.staffs = new ArrayList<>();
    }

    public int getBonusSalary() {
        return bonusSalary;
    }

    public void setBonusSalary(int bonusSalary) {
        this.bonusSalary = bonusSalary;
    }

}
