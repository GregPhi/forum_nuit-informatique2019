package com.example.forumnuitdelinformatique;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;
import com.example.forumnuitdelinformatique.view.company.CompanyListAdapter;
import com.example.forumnuitdelinformatique.view.company.CompanyViewModel;
import com.example.forumnuitdelinformatique.view.employee.EmployeeListAdapter;
import com.example.forumnuitdelinformatique.view.employee.EmployeeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static CompanyViewModel mCompanyViewModel;
    public static EmployeeViewModel mEmployeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewCompany = findViewById(R.id.recyclerview_company);
        final CompanyListAdapter adapterCompany = new CompanyListAdapter(this);
        recyclerViewCompany.setAdapter(adapterCompany);
        recyclerViewCompany.setLayoutManager(new LinearLayoutManager(this));

        mCompanyViewModel = ViewModelProviders.of(this).get(CompanyViewModel.class);
        mCompanyViewModel.getmAllCompany().observe(this, new Observer<List<Company>>() {
            @Override
            public void onChanged(@Nullable final List<Company> companys) {
                // Update the cached copy of the words in the adapter.
                adapterCompany.setCompanys(companys);
            }
        });

        mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
    }

    public void infosCompany(Company company){
        Intent intent = new Intent();
        startActivity(intent);
    }
}
