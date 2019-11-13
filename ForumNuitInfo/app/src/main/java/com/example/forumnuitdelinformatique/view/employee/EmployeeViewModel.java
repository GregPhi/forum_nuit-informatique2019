package com.example.forumnuitdelinformatique.view.employee;

import android.app.Application;

import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;
import com.example.forumnuitdelinformatique.view.company.CompanyRepository;

import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeRepository mRepository;

    private LiveData<List<Employee>> mAllEmployee;

    public EmployeeViewModel(Application application) {
        super(application);
        mRepository = new EmployeeRepository(application);
        mAllEmployee = mRepository.getAllEmployee();
    }

    public LiveData<List<Employee>> getmAllEmployee() { return mAllEmployee; }

    public void insert(Employee epmployee) { mRepository.insert(epmployee); }

    public void updateCompany(Employee... epmployee){
        mRepository.delete(epmployee[0]);
        mRepository.insert(epmployee[0]);
    }

    public void delete(Employee epmployee) { mRepository.delete(epmployee); }

    public LiveData<List<Employee>> getAllEmployeeForACompany(Company company){ return mRepository.getAllEmployeeForACompany(company);}
}
