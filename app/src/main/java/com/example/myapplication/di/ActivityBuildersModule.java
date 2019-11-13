package com.example.myapplication.di;

import com.example.myapplication.MainActivity;
import com.example.myapplication.employee.AddUpdateEmployeeFragment;
import com.example.myapplication.employee.EmployeeListFragment;
import com.example.myapplication.employee.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ActivityScoped
    @ContributesAndroidInjector()
    abstract MainActivity contributeImageActivity();

    @FragmentScope
    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragment();

    @FragmentScope
    @ContributesAndroidInjector
    abstract EmployeeListFragment provideEmployeeListFragment();


    @FragmentScope
    @ContributesAndroidInjector
    abstract AddUpdateEmployeeFragment provideAddOrUpdateEmployeeFragment();
}