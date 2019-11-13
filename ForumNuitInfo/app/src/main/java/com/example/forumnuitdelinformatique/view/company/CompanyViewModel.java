package com.example.forumnuitdelinformatique.view.company;

import android.app.Application;

import com.example.forumnuitdelinformatique.objet.Company;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class CompanyViewModel extends AndroidViewModel {
    private CompanyRepository mRepository;

    private LiveData<List<Company>> mAllCompany;

    public CompanyViewModel(Application application) {
        super(application);
        mRepository = new CompanyRepository(application);
        mAllCompany = mRepository.getmAllCompany();
    }

    public LiveData<List<Company>> getmAllCompany() { return mAllCompany; }

    public void insert(Company company) { mRepository.insert(company); }

    public void updateCompany(Company... contacts){
        mRepository.delete(contacts[0]);
        mRepository.insert(contacts[0]);
    }

    public void delete(Company company) { mRepository.delete(company); }

}
