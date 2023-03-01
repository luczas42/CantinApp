package org.apache.maven.cantinappdesktop.viewmodel;

import org.apache.maven.cantinappdesktop.model.Employee;
import org.apache.maven.cantinappdesktop.model.Scale;
import org.apache.maven.cantinappdesktop.repository.Repository;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.List;

public class ScaleDetailsViewModel {
    Repository repository = new Repository();

    public void getEmployeesWithClass(String _class, Callback<List<Employee>> getEmployeesCallback) {
        Call<List<Employee>> call = repository.getEmployeesWithClass(_class);
        call.enqueue(getEmployeesCallback);
    }

    public void deleteScale(int turnId, Callback<Void> deleteScaleCallback) {
        Call<Void> call = repository.deleteScale(turnId);
        call.enqueue(deleteScaleCallback);
    }

    public void addScale(String day, String period, String _class, List<Integer> idList, Callback<Scale> getScalesCallback) {
        Call<Scale> call = repository.addScale(day, period, _class, idList);
        call.enqueue(getScalesCallback);
    }

}
