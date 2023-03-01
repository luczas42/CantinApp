package com.example.cantinappmobile.view.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.cantinappmobile.model.Scale;
import com.example.cantinappmobile.repository.Repository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class ScalesViewModel extends ViewModel {
    private final SavedStateHandle state;
    public MutableLiveData<String> employeeSearchQuery = new MutableLiveData<>();
    private Repository repository = new Repository();

    private MutableLiveData<List<Scale>> _scaleLiveData = new MutableLiveData<>();
    public LiveData<List<Scale>> scaleLiveData = _scaleLiveData;

    public MutableLiveData<Connection> connectionLiveData = new MutableLiveData<>();

    public MutableLiveData<Connection> employeeConnectionLiveData = new MutableLiveData<>();
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

    public void getScales(){
        Call<List<Scale>> scaleCallback = repository.getScales();
        scaleCallback.enqueue(new Callback<List<Scale>>() {
            @Override
            public void onResponse(Call<List<Scale>> call, Response<List<Scale>> response) {
                if (response.isSuccessful() && response.body()!=null){
                    connectionLiveData.setValue(Connection.Successfull);
                    _scaleLiveData.setValue(response.body());
                }else{
                    connectionLiveData.setValue(Connection.Failed);
                }
            }

            @Override
            public void onFailure(Call<List<Scale>> call, Throwable t) {

            }
        });
    }
}
