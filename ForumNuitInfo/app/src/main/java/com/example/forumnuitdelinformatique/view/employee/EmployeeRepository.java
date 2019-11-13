package com.example.forumnuitdelinformatique.view.employee;

import android.app.Application;
import android.os.AsyncTask;

import com.example.forumnuitdelinformatique.ProjetRoomDatabase;
import com.example.forumnuitdelinformatique.dao.EmployeeDao;
import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;

public class EmployeeRepository {

    private EmployeeDao mEmployeeDao;
    private LiveData<List<Employee>> mAllEmployee;

    EmployeeRepository(Application application) {
        ProjetRoomDatabase db = ProjetRoomDatabase.getDatabase(application);
        mEmployeeDao = db.employeeDao();
        mAllEmployee = mEmployeeDao.getAllEmployee();
    }

    LiveData<List<Employee>> getAllEmployee() {
        return mAllEmployee;
    }

    LiveData<List<Employee>> getAllEmployeeForACompany(Company company){
        return mEmployeeDao.getAllEmployeeForACompany(company.getId());
    }

    public void insert (Employee employee) {
        new insertAsyncTask(mEmployeeDao).execute(employee);
    }

    public void delete (Employee employee) {
        new deleteAsyncTask(mEmployeeDao).execute(employee);
    }

    private static class insertAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao mAsyncTaskDao;

        insertAsyncTask(EmployeeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDao mAsyncTaskDao;

        deleteAsyncTask(EmployeeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}