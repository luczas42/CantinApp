package org.apache.maven.cantinappdesktop.viewmodel;

import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.repository.Repository;
import retrofit2.Call;
import retrofit2.Callback;

public class EmployeeDetailsViewModel {
    Repository repository = new Repository();

    public void addEmployee(String name, String _class, Callback<Employee> addEmployeeCallback) {
        Call<Employee> call = repository.addEmployee(name, _class);
        call.enqueue(addEmployeeCallback);
    }

    public void editEmployee(Employee myEmployee, Callback<Employee> editEmployeeCallback) {
        Call<Employee> call = repository.editEmployee(myEmployee);
        call.enqueue(editEmployeeCallback);
    }

    public void deleteEmployee(int id, Callback<Void> deleteEmployeeCallback) {
        Call<Void> call = repository.deleteEmployee(id);
        call.enqueue(deleteEmployeeCallback);
    }
}
