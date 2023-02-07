package com.example.cantinappmobile.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ScalesViewModel extends ViewModel {
    private final SavedStateHandle state;
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
}
