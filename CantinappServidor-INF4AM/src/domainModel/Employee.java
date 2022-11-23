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
    
    
}
