package com.example.forumnuitdelinformatique.view.company;

import android.app.Application;
import android.os.AsyncTask;

import com.example.forumnuitdelinformatique.ProjetRoomDatabase;
import com.example.forumnuitdelinformatique.dao.CompanyDao;
import com.example.forumnuitdelinformatique.objet.Company;

import java.util.List;

import androidx.lifecycle.LiveData;

public class CompanyRepository {

    private CompanyDao mCompanyDao;
    private LiveData<List<Company>> mAllCompany;

    CompanyRepository(Application application) {
        ProjetRoomDatabase db = ProjetRoomDatabase.getDatabase(application);
        mCompanyDao = db.companyDao();
        mAllCompany = mCompanyDao.getAllCompany();
    }

    LiveData<List<Company>> getmAllCompany() {
        return mAllCompany;
    }

    public Company getCompany(int id){
        List<Company> list = mCompanyDao.getCmpanys();
        Company company = null;
        for(Company c : list){
            if(c.getId() == id){
                company = c;
            }
        }
        return company;
    }

    public void insert (Company employee) {
        new insertAsyncTask(mCompanyDao).execute(employee);
    }

    public void delete (Company employee) {
        new deleteAsyncTask(mCompanyDao).execute(employee);
    }

    private static class insertAsyncTask extends AsyncTask<Company, Void, Void> {

        private CompanyDao mAsyncTaskDao;

        insertAsyncTask(CompanyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Company... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Company, Void, Void> {

        private CompanyDao mAsyncTaskDao;

        deleteAsyncTask(CompanyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Company... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
