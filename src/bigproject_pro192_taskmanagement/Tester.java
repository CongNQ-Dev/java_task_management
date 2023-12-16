package bigproject_pro192_taskmanagement;

import bigproject_pro192_taskmanagement.DAO.ManagerManagement;
import bigproject_pro192_taskmanagement.DAO.StaffManagement;
import bigproject_pro192_taskmanagement.DAO.TaskManagement;
import bigproject_pro192_taskmanagement.DTO.Manager;
import bigproject_pro192_taskmanagement.DTO.Staff;
import bigproject_pro192_taskmanagement.DTO.Task;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author cong.nguyen
 */
public class Tester {

    public static ManagerManagement managerManagement = new ManagerManagement();
    public static StaffManagement staffManagement = new StaffManagement();
    public static TaskManagement taskManagement = new TaskManagement();
    public static final Map<Integer, List<String>> workingLog = new HashMap<>();

    public static void main(String[] args) {
        int choice = 0;
        while (true) {
            try {
                System.out.println("======== MANAGEMENT ======");
                System.out.println("1. Staff Management");
                System.out.println("2. Manager Management");
                System.out.println("3. Task Management");
                System.out.println("4. Exit program.");
                System.out.print("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 4) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        staffMgt();
                        break;
                    case 2:
                        managerMgt();
                        break;
                    case 3:
                        taskMgt();
                        break;
                    case 4:
                        System.out.println("Thank you.");
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 4!");
            }
        }
    }

    public static void staffMgt() {

        int choice = 0;
        while (true) {
            System.out.println("======== STAFF MANAGEMENT ======");
            System.out.println("1. Add a staff");
            System.out.println("2. Update staff by id");
            System.out.println("3. Find staff by id");
            System.out.println("4. Delete staff by id");
            System.out.println("5. Add task to staff.");
            System.out.println("6. Back to menu");

            try {
                System.out.print("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        addStaff();
                        break;
                    case 2:
                        updateStaff();
                        break;
                    case 3:
                        findStaff();
                        break;
                    case 4:
                        deleteStaff();
                        break;
                    case 5:
                        addTaskToStaff();
                        break;
                    case 6:
                        return;
                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 6!");
            }
        }
    }
    
    public static void addTaskToStaff() {
        if (taskManagement.getSize() < 1) {
            System.err.println("There is must at least 1 task to add.");
            return;
        }
        if (staffManagement.getSize() < 1) {
            System.err.println("There is must at least 1 staff to add.");
            return;
        }

        while (true) {
            System.out.println("Please input task id to add: ");
            int taskId = new Scanner(System.in).nextInt();
            Task s = taskManagement.findTaskById(taskId);
            if (s == null) {
                System.out.println("The task does not exist with id " + taskId);
                continue;
            }
            if (s.getStaff() != null) {
                while (true) {
                    System.out.println("This task already in a staff with id " + s.getStaff().getId());
                    System.out.println("Do you want to delete task from that staff? (y/n): ");
                    String choice = new Scanner(System.in).nextLine();
                    if (choice.equalsIgnoreCase("n")) {
                        System.out.println("You chose not to add staff to task.");
                        return;
                    }
                    s.setStaff(null);
                    staffManagement.deleteTaskFromStaff(s.getId());
                    System.out.println("Successfully removed old staff from this task.");
                    break;
                }
            }
            Staff c = null;
            while (true) {
                System.out.println("Please input staff id to add: ");
                int staffId = new Scanner(System.in).nextInt();
                c = staffManagement.findStaffById(staffId);
                if (c == null) {
                    System.out.println("The staff does not exist with id " + staffId);
                    continue;
                }
                break;
            }

            while (true) {
                System.out.println("Do you really to add task " + taskId
                        + " to staff " + c.getId() + "(y/n)? ");
                String choice = new Scanner(System.in).nextLine();
                if (choice.equalsIgnoreCase("n")) {
                    System.out.println("You chose not to add task to staff.");
                    return;
                }
                if (staffManagement.addTaskToStaff(s, c)) {
                    
                    System.out.println("Successfully to add a task to staff.");
                } else {
                    System.out.println("Failed to add because this task is already existed.");
                }
                return;
            }
        }
    }

    
    public static void addStaff() {
        while (true) {
            Staff staff = new Staff();
            while (true) {
                System.out.println("Please input staff id: ");
                try {
                    int id = new Scanner(System.in).nextInt();
                    if (id < 1 || id > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        staff.setId(id);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Id must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }
            while (true) {
                System.out.println("Please input staff name: ");
                String name = new Scanner(System.in).nextLine();
                if (name.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                    System.err.println("Please input staff name from 1 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    staff.setName(name);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input staff gender (M/F): ");
                String gender = new Scanner(System.in).nextLine();
                if (!(gender.equals("M") || gender.equals("F"))) {
                    System.err.println("Staff gender must be male(M) or female(F).");
                } else {
                    staff.setGender(gender);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input basic salary: ");
                try {
                    int salary = new Scanner(System.in).nextInt();
                    if (salary < 1 || salary > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        staff.setBasicSalary(salary);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Basic salary must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }
            if (staffManagement.addStaff(staff)) {
                System.out.println("Successfully added a staff.");
            } else {
                System.err.println("The staff with id " + staff.getId() + " is existed.");
            }

            System.out.println("Do you want to add more? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
        }
    }

    public static void updateStaff() {

        System.out.println("Please input staff id: ");
        int updateId = new Scanner(System.in).nextInt();
        Staff updateStaff = staffManagement.findStaffById(updateId);
        if (updateStaff == null) {
            System.err.println("The staff with the provided Id doesn't exist.");
            return;
        }

        while (true) {
            System.out.println("Do you want to update name? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update name.");
                break;
            }

            System.out.println("Please input staff name: ");
            String name = new Scanner(System.in).nextLine();
            if (name.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                System.err.println("Please input staff name from 1 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateStaff.setName(name);
                break;
            }
        }

        while (true) {
            System.out.println("Do you want to update staff gender (y/n) ?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update staff gender.");
                break;
            } else {
                System.out.println("Please input staff gender (M/F): ");
                String gender = new Scanner(System.in).nextLine();
                if (!(gender.equals("M") || gender.equals("F"))) {
                    System.err.println("Staff gender must be male(M) or female(F).");
                } else {
                    updateStaff.setGender(gender);
                    break;
                }
            }
        }

        while (true) {
            System.out.println("Do you want to update basic salary? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update basic salary.");
                break;
            }
            System.out.println("Please input basic salary: ");
            try {
                int updateSalary = new Scanner(System.in).nextInt();
                if (updateSalary < 1 || updateSalary > Integer.MAX_VALUE) {
                    throw new InputMismatchException();
                }
                updateStaff.setBasicSalary(updateSalary);
                break;
            } catch (InputMismatchException e) {
                System.err.println("Basic Salary must be a positive integer!");
            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }

        }

        if (staffManagement.updateStaffById(updateStaff)) {
            System.out.println("Successfully updated the staff with id"
                    + updateStaff.getId());
        } else {
            System.out.println("The staff with id " + updateStaff.getId()
                    + " is not existed.");
        }

    }

    public static void findStaff() {
        System.out.println("Please input staff id: ");
        int id = new Scanner(System.in).nextInt();
        Staff staff = staffManagement.findStaffById(id);
        if (staff == null) {
            System.err.println("The staff with the provided Id doesn't exist.");
            return;
        }
        System.out.println("==========");
        System.out.println("The Staff you want to find: ");
        System.out.println("Id: " + staff.getId());
        System.out.println("Name: " + staff.getName());
        System.out.println("Gender: " + staff.getGender());
        System.out.println("Basic Salary: " + staff.getBasicSalary());
        System.out.println("==========");
    }

    public static void deleteStaff() {
        System.out.println("Please input staff id: ");
        int id = new Scanner(System.in).nextInt();
        Staff staff = staffManagement.findStaffById(id);
        if (staff == null) {
            System.out.println("The staff with the provided Id doesn't exist.");
            return;
        }
        while (true) {
            System.out.println("Do you want to delete this staff? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
            if (staffManagement.deleteStaffById(id)) {
                System.out.println("Successfully deleted staff with id " + id);
                return;
            }
        }
    }

    public static void managerMgt() {
        int choice = 0;
        while (true) {
            System.out.println("======== MANAGER MANAGEMENT ======");
            System.out.println("1. Add a manager");
            System.out.println("2. Update manager by id");
            System.out.println("3. Find manager by id");
            System.out.println("4. Delete manager by id");
            System.out.println("5. Add staff to manager.");
            System.out.println("6. Back to menu");
            try {
                System.out.print("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        addManager();
                        break;
                    case 2:
                        updateManager();
                        break;
                    case 3:
                        findManager();
                        break;
                    case 4:
                        deleteManager();
                        break;
                    case 5:
                        addStaffToManager();
                        break;
                    case 6:
                        return;
                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 6!");
            }
        }
    }
    
    public static void addStaffToManager() {
        
    }

    public static void addManager() {
        while (true) {
            Staff manager = new Manager();
            while (true) {
                System.out.println("Please input manager id: ");
                try {
                    int id = new Scanner(System.in).nextInt();
                    if (id < 1 || id > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        manager.setId(id);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Id must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }
            while (true) {
                System.out.println("Please input manager name: ");
                String name = new Scanner(System.in).nextLine();
                if (name.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                    System.err.println("Please input manager name from 1 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    manager.setName(name);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input manager gender (M/F): ");
                String gender = new Scanner(System.in).nextLine();
                if (!(gender.equals("M") || gender.equals("F"))) {
                    System.err.println("Manager gender must be male(M) or female(F).");
                } else {
                    manager.setGender(gender);
                    break;
                }
            }

            while (true) {
                System.out.println("Please input basic salary: ");
                try {
                    int salary = new Scanner(System.in).nextInt();
                    if (salary < 1 || salary > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        manager.setBasicSalary(salary);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Basic salary must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }

            while (true) {
                System.out.println("Please input bonus salary: ");
                try {
                    int salary = new Scanner(System.in).nextInt();
                    if (salary < 1 || salary > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        ((Manager)manager).setBonusSalary(salary);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Bonus salary must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }
            if (managerManagement.addManager(((Manager)manager))) {
                System.out.println("Successfully added an manager.");
            } else {
                System.err.println("The manager with id " + manager.getId() + " is existed.");
            }

            System.out.println("Do you want to add more? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
        }
    }

    public static void updateManager() {

        System.out.println("Please input manager id: ");
        int updateId = new Scanner(System.in).nextInt();
        Manager updateManager = managerManagement.findManagerById(updateId);
        if (updateManager == null) {
            System.err.println("The manager with the provided Id doesn't exist.");
            return;
        }

        while (true) {
            System.out.println("Do you want to update name? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update name.");
                break;
            }

            System.out.println("Please input owner name: ");
            String name = new Scanner(System.in).nextLine();
            if (name.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!name.matches("^[a-zA-Z\\s]{1,100}$")) {
                System.err.println("Please input manager name from 1 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateManager.setName(name);
                break;
            }
        }

        while (true) {
            System.out.println("Do you want to update manager gender (y/n) ?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update pet's gender.");
                break;
            } else {
                System.out.println("Please input manager gender (M/F): ");
                String gender = new Scanner(System.in).nextLine();
                if (!(gender.equals("M") || gender.equals("F"))) {
                    System.err.println("Manager gender must be male(M) or female(F).");
                } else {
                    updateManager.setGender(gender);
                    break;
                }
            }
        }

        while (true) {
            System.out.println("Do you want to update basic salary? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update basic salary.");
                break;
            }
            System.out.println("Please input basic salary: ");
            try {
                int updateSalary = new Scanner(System.in).nextInt();
                if (updateSalary < 1 || updateSalary > Integer.MAX_VALUE) {
                    throw new InputMismatchException();
                }
                updateManager.setBasicSalary(updateSalary);
                break;
            } catch (InputMismatchException e) {
                System.err.println("Basic Salary must be a positive integer!");
            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }

        }

        while (true) {
            System.out.println("Do you want to update bonus salary? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update bonus salary.");
                break;
            }
            System.out.println("Please input bonus salary: ");
            try {
                int updateSalary = new Scanner(System.in).nextInt();
                if (updateSalary < 1 || updateSalary > Integer.MAX_VALUE) {
                    throw new InputMismatchException();
                }
                updateManager.setBonusSalary(updateSalary);
                break;
            } catch (InputMismatchException e) {
                System.err.println("Bonus Salary must be a positive integer!");
            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }

        }

        if (managerManagement.updateManagerById(updateManager)) {
            System.out.println("Successfully updated the manager with id"
                    + updateManager.getId());
        } else {
            System.out.println("The manager with id " + updateManager.getId()
                    + " is not existed.");
        }

    }

    public static void findManager() {
        System.out.println("Please input manager id: ");
        int id = new Scanner(System.in).nextInt();
        Manager manager = managerManagement.findManagerById(id);
        if (manager == null) {
            System.err.println("The manager with the provided Id doesn't exist.");
            return;
        }
        System.out.println("==========");
        System.out.println("The Manager you want to find: ");
        System.out.println("Id: " + manager.getId());
        System.out.println("Name: " + manager.getName());
        System.out.println("Gender: " + manager.getGender());
        System.out.println("Basic Salary: " + manager.getBasicSalary());
        System.out.println("Bonus Salary: " + manager.getBonusSalary());
        System.out.println("==========");
    }

    public static void deleteManager() {
        System.out.println("Please input manager id: ");
        int id = new Scanner(System.in).nextInt();
        Manager manager = managerManagement.findManagerById(id);
        if (manager == null) {
            System.out.println("The manager with the provided Id doesn't exist.");
            return;
        }
        while (true) {
            System.out.println("Do you want to delete this manager? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
            if (managerManagement.deleteManagerById(id)) {
                System.out.println("Successfully deleted manager with id " + id);
                return;
            }
        }
    }

    public static void taskMgt() {
        int choice = 0;
        while (true) {
            System.out.println("======== TASK MANAGEMENT ======");
            System.out.println("1. Add a task");
            System.out.println("2. Update task by id");
            System.out.println("3. Find task by id");
            System.out.println("4. Delete task by id");
            System.out.println("5. Perform task.");
            System.out.println("6. Back to menu");

            try {
                System.out.print("Please choose: ");
                choice = new Scanner(System.in).nextInt();
                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException();
                }
                switch (choice) {
                    case 1:
                        addTask();
                        break;
                    case 2:
                        updateTask();
                        break;
                    case 3:
                        findTask();
                        break;
                    case 4:
                        deleteTask();
                        break;
                    case 5:
                        performTask();
                        break;
                    case 6:
                        return;
                }
            } catch (Exception e) {
                System.err.println("Only accept from 1 to 6!");
            }
        }
    }

    public static void performTask() {
        if (taskManagement.getSize() < 1) {
            System.err.println("Please add at least 1 task to do this.");
            return;
        }

        Staff staff = null;
        while (true) {
            System.out.println("Please input staff id: ");
            int staffid = new Scanner(System.in).nextInt();
            staff = staffManagement.findStaffById(staffid);
            if (staff != null) {
                break;
            } else {
                System.err.println("Staff is not found with provided id.");
            }
        }
        int workingHours = 0;
        while (true) {
            System.out.println("Please input working hours:  ");
            try {
                workingHours = new Scanner(System.in).nextInt();
                if (workingHours < 1 || workingHours > Integer.MAX_VALUE) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                System.err.println("Working Hours must be positive integer.");
            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }
        }
        Task task = null;
        while (true) {
            System.out.println("Please input task id ");
            int taskId = new Scanner(System.in).nextInt();
            task = taskManagement.findTaskById(taskId);
            if (task != null) {
                break;
            } else {
                System.err.println("Task is not found with provided id.");
            }
        }

        while (true) {

            if (task != null) {
                System.out.println("Do you want to assign task to this staff to task: ");
                System.out.println("Task Id: " + task.getId());
                System.out.println("Task Id: " + task.getId());
                System.out.println("Total Hours" + task.getTotalHours());
                System.out.println("Working Hours" + workingHours);

                System.out.println("Enter Y to assign, N to quit?");
                String choice = new Scanner(System.in).nextLine();
                if (choice.equalsIgnoreCase("y")) {
                    List<String> log = workingLog.get(task.getId());
                    if (log == null) {
                        log = new ArrayList<>();
                    }
                    log.add("Staff " + staff.getId() + " do task "
                            + task.getId() + "in " + workingHours + "/" + task.getTotalHours());
                    workingLog.put(staff.getId(), log);
                    System.out.println("Successfully added working log.");
                    return;
                } else if (choice.equalsIgnoreCase("n")) {
                    System.out.println("You chose to exit");
                    return;
                }
            } else {
                System.out.println("Task not found with id " + task.getId());
                continue;
            }
        }
    }

    public static void addTask() {
        while (true) {
            Task task = new Task();
            while (true) {
                System.out.println("Please input task id: ");
                try {
                    int id = new Scanner(System.in).nextInt();
                    if (id < 1 || id > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        task.setId(id);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Id must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }
            while (true) {
                System.out.println("Please input task title: ");
                String title = new Scanner(System.in).nextLine();
                if (title.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!title.matches("^[a-zA-Z\\s]{1,100}$")) {
                    System.err.println("Please input task title from 1 to 100 characters"
                            + " and must in alphabet!");
                } else {
                    task.setTitle(title);
                    break;
                }
            }
            Date beginDate = null;
            while (true) {
                System.out.println("Please input task Begin Date: ");
                String beginDateStr = new Scanner(System.in).nextLine();
                if (beginDateStr.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!beginDateStr.isBlank()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    try {
                        beginDate = sdf.parse(beginDateStr);
                        task.setBeginDate(beginDate);
                        break;
                    } catch (ParseException e) {
                        System.err.println("Invalid date detect. Please input again.");
                    }
                }
            }

            while (true) {
                System.out.println("Please input task End Date: ");
                String endDateStr = new Scanner(System.in).nextLine();
                if (endDateStr.isBlank()) {
                    System.err.println("Your input is Empty!");
                } else if (!endDateStr.isBlank()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    sdf.setLenient(false);
                    try {
                        Date endDate = sdf.parse(endDateStr);
                        if (endDate.before(beginDate)) {
                            throw new IllegalArgumentException();
                        }
                        task.setEndDate(endDate);
                        break;
                    } catch (ParseException e) {
                        System.err.println("Invalid date detect. Please input again.");
                    } catch (IllegalArgumentException e) {
                        System.err.println("End date must after begin date.");
                    }
                }
            }

            while (true) {
                System.out.println("Please input total hours: ");
                try {
                    int totalHours = new Scanner(System.in).nextInt();
                    if (totalHours < 1 || totalHours > Integer.MAX_VALUE) {
                        throw new InputMismatchException();
                    } else {
                        task.setTotalHours(totalHours);
                        break;
                    }
                } catch (InputMismatchException e) {
                    System.err.println("Total Hours must be positive integer.");

                } catch (Exception e) {
                    System.err.println("Invalid Input!");
                }
            }
            if (taskManagement.addTask(task)) {
                System.out.println("Successfully added a task.");
            } else {
                System.err.println("The task with id " + task.getId() + " is existed.");
            }

            System.out.println("Do you want to add more? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
        }
    }

    public static void updateTask() {

        System.out.println("Please input task id: ");
        int updateId = new Scanner(System.in).nextInt();
        Task updateTask = taskManagement.findTaskById(updateId);
        if (updateTask == null) {
            System.err.println("The task with the provided Id doesn't exist.");
            return;
        }

        while (true) {
            System.out.println("Do you want to update title? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to update title.");
                break;
            }

            System.out.println("Please input title name: ");
            String title = new Scanner(System.in).nextLine();
            if (title.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!title.matches("^[a-zA-Z\\s]{1,100}$")) {
                System.err.println("Please input title from 1 to 100 characters"
                        + "and must in alphabet.");
            } else {
                updateTask.setTitle(title);
                break;
            }
        }

        while (true) {
            System.out.println("Do you want to update Begin Day? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to Begin Day.");
                break;
            }

            Date beginDate = null;

            System.out.println("Please input task Begin Date: ");
            String beginDateStr = new Scanner(System.in).nextLine();
            if (beginDateStr.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!beginDateStr.isBlank()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                try {
                    beginDate = sdf.parse(beginDateStr);
                    updateTask.setBeginDate(beginDate);
                    break;
                } catch (ParseException e) {
                    System.err.println("Invalid date detect. Please input again.");
                }

            }
        }

        while (true) {
            System.out.println("Do you want to update End Day? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to End Day.");
                break;
            }

            Date endDate = null;
            System.out.println("Please input task End Date: ");
            String endDateStr = new Scanner(System.in).nextLine();
            if (endDateStr.isBlank()) {
                System.err.println("Your input is Empty!");
            } else if (!endDateStr.isBlank()) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                try {
                    endDate = sdf.parse(endDateStr);
                    updateTask.setEndDate(endDate);
                    break;
                } catch (ParseException e) {
                    System.err.println("Invalid date detect. Please input again.");
                }

            }
        }

        while (true) {
            System.out.println("Do you want to update total hours? (y/n): ");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                System.out.println("You chose not to total hours.");
                break;
            }
            System.out.println("Please input total hours: ");
            try {
                int totalHours = new Scanner(System.in).nextInt();
                if (totalHours < 1 || totalHours > Integer.MAX_VALUE) {
                    throw new InputMismatchException();
                } else {
                    updateTask.setTotalHours(totalHours);
                    break;
                }
            } catch (InputMismatchException e) {
                System.err.println("Total Hours must be positive integer.");

            } catch (Exception e) {
                System.err.println("Invalid Input!");
            }

        }

    }

    public static void findTask() {
        System.out.println("Please input task id: ");
        int id = new Scanner(System.in).nextInt();
        Task task = taskManagement.findTaskById(id);
        if (task == null) {
            System.err.println("The task with the provided Id doesn't exist.");
            return;
        }
        System.out.println("==========");
        System.out.println("The Task you want to find: ");
        System.out.println("Id: " + task.getId());
        System.out.println("Title: " + task.getTitle());
        System.out.println("Begin Date: " + new SimpleDateFormat("dd/MM/yyyy").format(task.getBeginDate()));
        System.out.println("End Date: " + new SimpleDateFormat("dd/MM/yyyy").format(task.getBeginDate()));
        System.out.println("Total Hours: " + task.getTotalHours());
        List<String> log = workingLog.get(task.getId());
        if (log != null && log.size() > 0) {
            for (int i = 0; i < log.size(); i++) {
                System.out.println(log.get(i));;
            }
        }
        System.out.println("==========");
    }

    public static void deleteTask() {
        System.out.println("Please input task id: ");
        int id = new Scanner(System.in).nextInt();
        Task task = taskManagement.findTaskById(id);
        if (task == null) {
            System.out.println("The task with the provided Id doesn't exist.");
            return;
        }
        while (true) {
            System.out.println("Do you want to delete this task? (y/n)?");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("n") || choice.equals("N")) {
                return;
            }
            if (taskManagement.deleteTaskById(id)) {
                System.out.println("Successfully deleted task with id " + id);
                return;
            }
        }
    }

}
