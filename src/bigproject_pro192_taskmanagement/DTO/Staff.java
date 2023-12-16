
package bigproject_pro192_taskmanagement.DTO;

import java.util.ArrayList;
import java.util.List;

public class Staff {
    private int id;
    private String name;
    private String gender;
    private int basicSalary;
    private List<Task> tasks;

    public Staff() {
        this.tasks=new ArrayList<>();
    }

    public Staff(int id, String name, String gender, int basicSalary) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.basicSalary = basicSalary;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }
    
}
