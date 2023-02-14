package org.apache.maven.cantinappdesktop.model;

import java.util.ArrayList;

public class Scale {
    private Turn turn;
    private ArrayList<Employee> employeeList;

    public Scale(Turn turn, ArrayList<Employee> employeeList) {
        this.turn = turn;
        this.employeeList = employeeList;
    }

    public Turn getTurn() {
        return turn;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
