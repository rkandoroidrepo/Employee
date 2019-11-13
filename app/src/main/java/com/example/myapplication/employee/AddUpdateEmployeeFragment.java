package com.example.myapplication.employee;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.myapplication.R;
import com.example.myapplication.modal.Employee;
import com.example.myapplication.modal.Employee2;
import com.example.myapplication.viewModal.EmployeeViewModalIml;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUpdateEmployeeFragment extends DaggerFragment {

    private static final String EMPLOYEE_KEY = "employee";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @BindView(R.id.edit_employee_name)
    EditText edtName;
    @BindView(R.id.edit_employee_age)
    EditText edtAge;
    @BindView(R.id.edit_employee_salary)
    EditText edtSalary;
    @BindView(R.id.btn_submit)
    Button submit;
    @BindView(R.id.btn_cancel)
    Button cancel;
    @BindView(R.id.loader_view)
    FrameLayout loaderLayout;
    private Unbinder unbinder;
    private EmployeeViewModalIml employeeViewModal;
    private View rootView;
    private Employee employee;
    private boolean isUpdate;

    public AddUpdateEmployeeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_add_or_update_employee, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmployeeData();
        employeeViewModal = ViewModelProviders.of(getActivity(), viewModelFactory).get(EmployeeViewModalIml.class);
        setLoader();

    }

    private void setEmployeeData() {
        if (getArguments() != null) {
            employee = getArguments().getParcelable(EMPLOYEE_KEY);
        }
        if (employee != null) {
            isUpdate = true;
            edtName.setText(employee.getName());
            edtSalary.setText(employee.getSalary());
            edtAge.setText(employee.getAge());
        }
    }

    private void setLoader() {
        employeeViewModal.getLoadingStatus().observe(this,
                aBoolean -> {
                    if (aBoolean != null) {
                        loaderLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                    }
                });
    }

    @OnClick(R.id.btn_submit)
    void onSubmitAction() {
        if(employee == null){
            employee = new Employee();
        }
        employee.setName(edtName.getText().toString());
        employee.setSalary(edtSalary.getText().toString());
        employee.setAge(edtAge.getText().toString());
        if (isUpdate) {
            Employee2 employee2 = new Employee2();
            employee2.setId(employee.getId());
            employee2.setAge(employee.getAge());
            employee2.setName(employee.getName());
            employee2.setSalary(employee.getSalary());
            employeeViewModal.updateEmployee(employee2).observe(this, employee ->
                    Navigation.findNavController(rootView).popBackStack());
        } else {
            Employee2 employee2 = new Employee2();
            employee2.setAge(edtAge.getText().toString());
            employee2.setName(edtName.getText().toString());
            employee2.setSalary(edtSalary.getText().toString());
            employeeViewModal.addNewEmployee(employee2).observe(this, employee21 -> {
                if(employee21 != null){
                    Navigation.findNavController(rootView).popBackStack();
                }
            });
        }

    }

    @OnClick(R.id.btn_cancel)
    void onCancelAction() {
        Navigation.findNavController(rootView).popBackStack();
    }
}
