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
    public static PropositionViewModel mPropositionViewModel;

    private ImageView mLogoImageView;
    private TextView mNomTextView;
    private TextView mDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infos_company);
        Intent intent = getIntent();
        Company current = intent.getParcelableExtra("Company");
        int id = intent.getIntExtra("Id",0);
        int l = intent.getIntExtra("Logo",-666);

        String n = current.getNom();
        String d = current.getDescription();
        System.out.println("logo :"+l);
        current.setId(id);

        mNomTextView = findViewById(R.id.name_company);
        mDescriptionTextView = findViewById(R.id.description);
        mLogoImageView = findViewById(R.id.logo);
        mNomTextView.setText(n);
        mDescriptionTextView.setText(d);
        if(l!=-666){
            //mLogoImageView.setImageResource(l);
        }

        RecyclerView recyclerViewEmployee = findViewById(R.id.infos_employees);
        final EmployeeListAdapter adapterEmployee = new EmployeeListAdapter();
        recyclerViewEmployee.setAdapter(adapterEmployee);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(this));

        mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        mEmployeeViewModel.getAllEmployeeForACompany(current).observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable final List<Employee> employees) {
                System.out.println("taille liste emp : "+employees.size());
                // Update the cached copy of the words in the adapter.
                adapterEmployee.setEmployees(employees);
            }
        });

        RecyclerView recyclerViewProposition = findViewById(R.id.infos_proposition);
        final PropositionListAdapter adapterCompany = new PropositionListAdapter();
        recyclerViewProposition.setAdapter(adapterCompany);
        recyclerViewProposition.setLayoutManager(new LinearLayoutManager(this));

        mPropositionViewModel = ViewModelProviders.of(this).get(PropositionViewModel.class);
        mPropositionViewModel.getmAllPropositionForACompany(current).observe(this, new Observer<List<Proposition>>() {
            @Override
            public void onChanged(@Nullable final List<Proposition> propositions) {
                // Update the cached copy of the words in the adapter.
                adapterCompany.setPropositions(propositions);
            }
        });

    }
}
