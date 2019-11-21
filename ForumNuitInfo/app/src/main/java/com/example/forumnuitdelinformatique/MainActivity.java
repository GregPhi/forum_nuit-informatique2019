package com.example.forumnuitdelinformatique;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;
import com.example.forumnuitdelinformatique.view.company.CompanyListAdapter;
import com.example.forumnuitdelinformatique.view.company.CompanyViewModel;
import com.example.forumnuitdelinformatique.view.employee.EmployeeListAdapter;
import com.example.forumnuitdelinformatique.view.employee.EmployeeViewModel;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static CompanyViewModel mCompanyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
*/
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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_credits:
                Intent intent = new Intent(this, CreditsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void infoCompany(Company company){
        Intent intent = new Intent(this,InfoActivity.class);
        intent.putExtra("Company",company);
        intent.putExtra("Id",company.getId());
        intent.putExtra("Logo",company.getLogo());
        startActivity(intent);
    }

    public void intereesedCompany(Company company){
        boolean interessed = company.getInteressed();
        company.setInteressed(!interessed);
        mCompanyViewModel.insert(company);
    }
}
