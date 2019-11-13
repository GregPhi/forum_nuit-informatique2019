package com.example.forumnuitdelinformatique.view.company;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forumnuitdelinformatique.MainActivity;
import com.example.forumnuitdelinformatique.R;
import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;
import com.example.forumnuitdelinformatique.view.employee.EmployeeListAdapter;
import com.example.forumnuitdelinformatique.view.employee.EmployeeViewModel;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CompanyListAdapter extends RecyclerView.Adapter<CompanyListAdapter.CompanyViewHolder> {
    class CompanyViewHolder extends RecyclerView.ViewHolder{
        private final ImageView logoItemView;
        private final TextView nomItemView;
        private final Button bI;

        private CompanyViewHolder(final View itemView){
            super(itemView);
            logoItemView = itemView.findViewById(R.id.logo);
            nomItemView = itemView.findViewById(R.id.name_company);
            bI = itemView.findViewById(R.id.info);
        }
    }

    private final MainActivity mContext;
    private final LayoutInflater mInflater;
    private List<Company> mCompanys;

    public CompanyListAdapter(MainActivity context){
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public CompanyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_main_item, parent, false);
        return new CompanyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CompanyViewHolder holder, int position) {
        RecyclerView recyclerViewEmployee = holder.itemView.findViewById(R.id.recyclerview_employee);
        final EmployeeListAdapter adapterEmpployee = new EmployeeListAdapter();
        recyclerViewEmployee.setAdapter(adapterEmpployee);
        recyclerViewEmployee.setLayoutManager(new LinearLayoutManager(null));

        if (mCompanys != null) {
            final Company current = mCompanys.get(position);
            holder.nomItemView.setText(current.getNom());
            String logo = current.getLogo(); // R.drawable.name_logo
            if(!logo.equals("")){
                holder.logoItemView.setImageResource(Integer.valueOf(current.getLogo()));
            }
            holder.bI.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //mContext.infoCompany(current);
                }
            });

            EmployeeViewModel mEmployeeViewModel = MainActivity.mEmployeeViewModel;
            mEmployeeViewModel.getAllEmployeeForACompany(current).observe(null, new Observer<List<Employee>>() {
                @Override
                public void onChanged(@Nullable final List<Employee> employees) {
                    // Update the cached copy of the words in the adapter.
                    adapterEmpployee.setEmployees(employees);
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Nom");
        }
    }

    public void setCompanys(List<Company> companys){
        mCompanys = companys;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCompanys != null)
            return mCompanys.size();
        else return 0;
    }
}
