package domainModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Employee implements Serializable {
    private static final long serialVersioUID = 123456789L;
    private String name;
    private String grade;
    private ArrayList<Date> workDays;

    public Employee(String name, String grade, ArrayList<Date> workDays) {
        this.name = name;
        this.grade = grade;
        this.workDays = workDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ArrayList<Date> getWorkDays() {
        return workDays;
    }

    public void setWorkDays(ArrayList<Date> workDays) {
        this.workDays = workDays;
    }

    
}
