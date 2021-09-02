package com.example.augmantedreality.ui.performance;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PerfomanceViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PerfomanceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is perfomance fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}