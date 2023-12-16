package bigproject_pro192_taskmanagement.DAO;

import bigproject_pro192_taskmanagement.DTO.Staff;
import bigproject_pro192_taskmanagement.DTO.Task;
import java.util.ArrayList;
import java.util.List;

public class TaskManagement {

    private List<Task> tasks = new ArrayList<>();

    public boolean addTask(Task t) {
        if (findTaskById(t.getId()) != null) {
            return false;
        }
        return tasks.add(t);
    }

    public Task findTaskById(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                return tasks.get(i);
            }
        }
        return null;
    }

    public boolean updateTaskById(Task t) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == t.getId()) {
                tasks.set(i, t);
            }
        }
        return false;
    }

    public boolean deleteTaskById(int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == id) {
                tasks.remove(i);
                return true;
            }
        }
        return false;
    }
    
    public int getSize() {
        return tasks.size();
    }
    
//     public boolean addStaffToTask(Task t, Staff s) {
//        List<Staff> staffs = t.getTasks();
//        for (int i = 0; i < tasks.size(); i++) {
//            Task currentTaskInStaff = tasks.get(i);
//            if (t.getId()==(currentTaskInStaff.getId())) {
//                return false;
//            }
//        }
//        return tasks.add(t);
//    }
//
//    public void deleteTaskFromStaff(int id) {
//        for (int i = 0; i < staffs.size(); i++) {
//            List<Task> tasksInStaff = staffs.get(i).getTasks();
//            for (int j = 0; j < tasksInStaff.size(); j++) {
//                if (tasksInStaff.get(i).getId()==id) {
//                    tasksInStaff.remove(j);
//                }
//            }
//        }
//    }
}
