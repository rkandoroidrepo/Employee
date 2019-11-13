package com.example.myapplication.employee;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String EMPLOYEE_ID = "employeeId";
    @BindView(R.id.get_all_employee)
    Button btnGetAllEmployee;
    @BindView(R.id.get_by_id)
    Button btnGetEmployeeById;
    @BindView(R.id.add_new_employee)
    Button btnAddNewEmployee;
    @BindView(R.id.edt_employee_id)
    EditText edtEmployeeId;
    private View rootView;
    private Unbinder unbinder;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.get_all_employee)
    void actionGetAllEmployee() {
        Navigation.findNavController(rootView).navigate(R.id.action_home_to_employee_list);
    }

    @OnClick(R.id.get_by_id)
    void actionGetEmployeeId() {
        String employeeId = edtEmployeeId.getText().toString();
        if (!TextUtils.isEmpty(employeeId)) {
            Bundle bundle = new Bundle();
            bundle.putString(EMPLOYEE_ID, employeeId);
            Navigation.findNavController(rootView).navigate(R.id.action_home_to_employee_list, bundle);
        }else {
            Toast.makeText(getContext(), getContext().getString(R.string.enter_employee_id_error), Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.add_new_employee)
    void actionAddNewEmployee() {
        Navigation.findNavController(rootView).navigate(R.id.action_home_to_add_new_employee);
    }
}
