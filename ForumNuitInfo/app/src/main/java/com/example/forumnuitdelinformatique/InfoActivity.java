package com.example.forumnuitdelinformatique;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;
import com.example.forumnuitdelinformatique.objet.Proposition;
import com.example.forumnuitdelinformatique.view.employee.EmployeeListAdapter;
import com.example.forumnuitdelinformatique.view.employee.EmployeeViewModel;
import com.example.forumnuitdelinformatique.view.proposition.PropositionListAdapter;
import com.example.forumnuitdelinformatique.view.proposition.PropositionViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class InfoActivity extends AppCompatActivity {
    public static EmployeeViewModel mEmployeeViewModel;

    private ImageView mLogoImageView;
    private TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_company);
        Intent intent = getIntent();
        Company current = intent.getParcelableExtra("Company");
        int id = intent.getIntExtra("Id",0);
        int l = intent.getIntExtra("Logo",-666);

        String d = current.getDescription();
        current.setId(id);

        mDescriptionTextView = findViewById(R.id.description);
        mLogoImageView = findViewById(R.id.icon);
        mDescriptionTextView.setText(d);
        if(l!=-666){
            mLogoImageView.setImageResource(l);
        }

        RecyclerView recyclerViewEmployee = findViewById(R.id.infos_employees);
        final EmployeeListAdapter adapterEmployee = new EmployeeListAdapter();
        recyclerViewEmployee.setAdapter(adapterEmployee);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(this));

        mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        mEmployeeViewModel.getAllEmployeeForACompany(current).observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable final List<Employee> employees) {
                // Update the cached copy of the words in the adapter.
                adapterEmployee.setEmployees(employees);
            }
        });
    }
}
