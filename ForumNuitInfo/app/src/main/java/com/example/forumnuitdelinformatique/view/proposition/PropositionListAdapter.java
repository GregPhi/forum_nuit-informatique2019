package com.example.forumnuitdelinformatique.view.proposition;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.forumnuitdelinformatique.R;
import com.example.forumnuitdelinformatique.objet.Proposition;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PropositionListAdapter extends RecyclerView.Adapter<PropositionListAdapter.PropositionViewHolder> {
    class PropositionViewHolder extends RecyclerView.ViewHolder{
        private final TextView nomItemView;
        private final TextView formSearItemView;
        private final TextView descripItemView;

        private PropositionViewHolder(final View itemView){
            super(itemView);
            nomItemView = itemView.findViewById(R.id.name_proposition);
            formSearItemView = itemView.findViewById(R.id.form_search);
            descripItemView = itemView.findViewById(R.id.prop_descript);
        }
    }

    private List<Proposition> mPropositions;

    public PropositionListAdapter(){
    }

    @Override
    public PropositionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_proposition_item, parent, false);
        return new PropositionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropositionViewHolder holder, int position) {
        if (mPropositions != null) {
            final Proposition current = mPropositions.get(position);
            holder.nomItemView.setText(current.getNom());
            holder.formSearItemView.setText(current.getForm_search());
            holder.descripItemView.setText(current.getDescription());
        } else {
            // Covers the case of data not being ready yet.
            holder.nomItemView.setText("Proposition");
            holder.formSearItemView.setText("Formation recherchée");
            holder.descripItemView.setText("Description");
        }
    }

    public void setPropositions(List<Proposition> propositions){
        mPropositions = propositions;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPropositions != null)
            return mPropositions.size();
        else return 0;
    }
}
