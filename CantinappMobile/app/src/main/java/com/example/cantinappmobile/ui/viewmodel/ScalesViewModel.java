package com.example.cantinappmobile.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ScalesViewModel extends ViewModel {

    public MutableLiveData<Boolean> _openFilter;
    public LiveData<Boolean> openFilters =_openFilter;
}
