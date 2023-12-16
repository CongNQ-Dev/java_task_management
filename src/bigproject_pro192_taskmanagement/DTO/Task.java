package bigproject_pro192_taskmanagement.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {

    private int id;
    private String title;
    private Date beginDate;
    private Date endDate;
    private int totalHours;
    private Staff staff;

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Task() {

    }

    public Task(int id, String title, Date beginDate, Date endDate, int totalHours) {
        this.id = id;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.totalHours = totalHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    @Override
    public String toString() {
        return "Task{" + "id=" + id + ", title=" + title + ", beginDate=" + beginDate + ", endDate=" + endDate + ", totalHours=" + totalHours + '}';
    }

}
