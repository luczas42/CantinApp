package com.example.cantinappmobile.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.Employee;
import com.example.cantinappmobile.model.Turn;
import com.example.cantinappmobile.repository.RepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ScalesViewModel extends ViewModel {
    private final SavedStateHandle state;
    private RepositoryImpl repository = new RepositoryImpl();

    private MutableLiveData<List<Turn>> _turnLiveData = new MutableLiveData<>();
    public LiveData<List<Turn>> turnLiveData = _turnLiveData;
    public MutableLiveData<Boolean> displayFilters;

    @Inject
    public ScalesViewModel(SavedStateHandle state) {
        this.state = state;
        displayFilters = state.getLiveData("displayValue", false);
    }

    public void setDisplayValue(){
        displayFilters.setValue(!displayFilters.getValue());
    }

    public LiveData<Boolean> getDisplayValue(){
        return  displayFilters;
    }

    public void getTurns(){
        Call<List<Turn>> turnCall = repository.getTurn();
        turnCall.enqueue(new Callback<List<Turn>>() {
            @Override
            public void onResponse(Call<List<Turn>> call, Response<List<Turn>> response) {
                if (response.body()!=null){
                    _turnLiveData.setValue(response.body());
                    int position = 0;
                    for (Turn turns :
                            turnLiveData.getValue()) {
                        getTurnEmployees(turns.getId(), position);
                        position++;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Turn>> call, Throwable t) {

            }
        });
    }

    private void getTurnEmployees(int turnId, int position) {
        Call<List<Employee>> employeeCall = repository.getTurnEmployees(turnId);

        employeeCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                _turnLiveData.getValue().get(position).setEmployeeList(response.body());
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {

            }
        });
    }
}