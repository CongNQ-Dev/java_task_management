
package bigproject_pro192_taskmanagement.DAO;

import bigproject_pro192_taskmanagement.DTO.Manager;
import java.util.ArrayList;
import java.util.List;


public class ManagerManagement {

    private List<Manager> managers = new ArrayList<>();

    public boolean addManager(Manager m) {
        if(findManagerById(m.getId())!=null) {
            return false;
        }
        return managers.add(m);
    }

    public Manager findManagerById(int id) {
        for (int i = 0; i < managers.size(); i++) {
            if(managers.get(i).getId()==id)
            return managers.get(i);
        }
        return null;
    }

    public boolean deleteManagerById(int id) {
        for(int i=0; i<managers.size(); i++)
            if(managers.get(i).getId()==id) {
                managers.remove(i);
                return true;
            }
        return false;
    }

    public boolean updateManagerById(Manager m) {
        for(int i=0 ; i<managers.size(); i++) {
            if(managers.get(i).getId()==m.getId())
                managers.set(i,m);
            return true;
            
        }
        return false;
    }

}
