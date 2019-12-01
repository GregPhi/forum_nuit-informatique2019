package com.example.forumnuitdelinformatique.view.employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.forumnuitdelinformatique.InfoActivity;
import com.example.forumnuitdelinformatique.MainActivity;
import com.example.forumnuitdelinformatique.R;
import com.example.forumnuitdelinformatique.objet.Employee;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {
    class EmployeeViewHolder extends RecyclerView.ViewHolder{
        private final TextView nomItemView;
        private final TextView prenomItemView;
        private final TextView mailItemView;
        private final TextView postetemView;

        private EmployeeViewHolder(final View itemView){
            super(itemView);
            nomItemView = itemView.findViewById(R.id.name_employee);
            prenomItemView = itemView.findViewById(R.id.lastname);
            mailItemView = itemView.findViewById(R.id.mail);
            mailItemView.setVisibility(View.INVISIBLE);
            postetemView = itemView.findViewById(R.id.poste);
            postetemView.setVisibility(View.INVISIBLE);
        }
    }

    private List<Employee> mEmployees;

    public EmployeeListAdapter(){
    }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_employee_item, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        if (mEmployees != null) {
            final Employee current = mEmployees.get(position);
            holder.nomItemView.setText(current.getNom());
            holder.prenomItemView.setText(current.getPrenom());
            if(!current.getMail().equals("")){
                holder.mailItemView.setText(current.getMail());
                holder.mailItemView.setVisibility(View.VISIBLE);
            }
            if(!current.getPoste().equals("")){
                holder.postetemView.setText(current.getPoste());
                holder.postetemView.setVisibility(View.VISIBLE);
            }
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Nom");
            holder.prenomItemView.setText("Prénom");
            holder.mailItemView.setText("Mail");
            holder.postetemView.setText("Poste");
        }
    }

    public void setEmployees(List<Employee> employees){
        mEmployees = employees;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEmployees != null)
            return mEmployees.size();
        else return 0;
    }
}

