package com.example.forumnuitdelinformatique.view.proposition;

import android.app.Application;

import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Proposition;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class PropositionViewModel extends AndroidViewModel {
    private PropositionRepository mRepository;

    private LiveData<List<Proposition>> mAllEmployee;

    public PropositionViewModel(Application application) {
        super(application);
        mRepository = new PropositionRepository(application);
        mAllEmployee = mRepository.getAllProposition();
    }

    public LiveData<List<Proposition>> getAllProposition() { return mAllEmployee; }

    public void insert(Proposition proposition) { mRepository.insert(proposition); }

    public void updateCompany(Proposition... proposition){
        mRepository.delete(proposition[0]);
        mRepository.insert(proposition[0]);
    }

    public void delete(Proposition proposition) { mRepository.delete(proposition); }

    public LiveData<List<Proposition>> getmAllPropositionForACompany(Company company){ return mRepository.getmAllPropositionForACompany(company);}
}
