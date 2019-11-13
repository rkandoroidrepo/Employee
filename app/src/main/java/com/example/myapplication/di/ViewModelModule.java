package com.example.myapplication.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.viewModal.EmployeeViewModalIml;
import com.example.myapplication.viewModal.MyViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeViewModalIml.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindMainViewModal(EmployeeViewModalIml articleListViewModel);

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(MyViewModelFactory viewModelFactory);

}