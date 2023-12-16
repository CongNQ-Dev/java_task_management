package bigproject_pro192_taskmanagement.DAO;

import bigproject_pro192_taskmanagement.DTO.Staff;
import bigproject_pro192_taskmanagement.DTO.Task;
import java.util.ArrayList;
import java.util.List;

public class StaffManagement {

    private List<Staff> staffs = new ArrayList<>();

    
    public int getSize() {
        return staffs.size();
    }
    public boolean addStaff(Staff s) {
        for (int i = 0; i < staffs.size(); i++) {
            if (findStaffById(s.getId()) != null) {
                return false;
            }
        }
        return staffs.add(s);
    }

    public Staff findStaffById(int id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId() == id) {
                return staffs.get(i);
            }
        }
        return null;
    }

    public boolean updateStaffById(Staff s) {  //id
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId() == s.getId()) {
                staffs.set(i, s);
                return true;
            }
        }
        return false;

    }

    public boolean deleteStaffById(int id) {
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getId() == id) {
                staffs.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public boolean addTaskToStaff(Task t, Staff s) {
        List<Task> tasks = s.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTaskInStaff = tasks.get(i);
            if (t.getId()==(currentTaskInStaff.getId())) {
                return false;
            }
        }
        return tasks.add(t);
    }

    public void deleteTaskFromStaff(int id) {
        for (int i = 0; i < staffs.size(); i++) {
            List<Task> tasksInStaff = staffs.get(i).getTasks();
            for (int j = 0; j < tasksInStaff.size(); j++) {
                if (tasksInStaff.get(i).getId()==id) {
                    tasksInStaff.remove(j);
                }
            }
        }
    }

}
