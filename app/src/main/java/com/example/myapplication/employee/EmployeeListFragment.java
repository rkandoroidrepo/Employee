package com.example.myapplication.employee;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.modal.Employee;
import com.example.myapplication.viewModal.EmployeeViewModalIml;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmployeeListFragment extends DaggerFragment implements EmployeeListAdapter.ItemClickListener {

    private static final String SUCCESSFULLY = "successfully";
    private static final String EMPLOYEE_ID = "employeeId";
    private static final String EMPLOYEE = "employee";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @BindView(R.id.loader_view)
    FrameLayout loaderLayout;
    @BindView(R.id.employee_list)
    RecyclerView recyclerView;
    private EmployeeViewModalIml employeeViewModal;
    private View rootView;
    private String employeeId;
    private Unbinder unbinder;

    public EmployeeListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_employee_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getArguments() != null) {
            employeeId = getArguments().getString(EMPLOYEE_ID);
        }
        employeeViewModal = ViewModelProviders.of(getActivity(), viewModelFactory).get(EmployeeViewModalIml.class);
        if (employeeId == null) {
            loadEmployeeList();
        } else {
            loadEmployeeById();
        }
        setLoadingStatus();
    }

    private void setLoadingStatus() {
        employeeViewModal.getLoadingStatus().observe(this,
                aBoolean -> {
                    if (aBoolean != null) {
                        loaderLayout.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                    }
                });
    }

    private void loadEmployeeById() {
        employeeViewModal.getEmployee(employeeId).observe(this, employee -> {
            if (employee != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                List<Employee> employeeList = new ArrayList<>();
                employeeList.add(employee);
                EmployeeListAdapter adapter = new EmployeeListAdapter(employeeList, this);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), getContext().getString(R.string.error_employee_by_id),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadEmployeeList() {
        employeeViewModal.getEmployee(true).observe(this, employees -> {
            if (employees != null) {
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                EmployeeListAdapter adapter = new EmployeeListAdapter(employees, this);
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(getContext(), getContext().getString(R.string.error_employee_list),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onEdit(Employee employee) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EMPLOYEE, employee);
        Navigation.findNavController(rootView).navigate(R.id.action_employee_list_to_update_employee,
                bundle);
    }

    @Override
    public void onDelete(String id) {
        employeeViewModal.deleteEmployee(id).observe(this, deleteResponse -> {
            if (deleteResponse.getSuccess().getText().equals(SUCCESSFULLY)) {
                employeeViewModal.getEmployee(true);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
