package com.example.forumnuitdelinformatique.view.proposition;

import android.app.Application;
import android.os.AsyncTask;

import com.example.forumnuitdelinformatique.ProjetRoomDatabase;
import com.example.forumnuitdelinformatique.dao.PropositionDao;
import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Proposition;

import java.util.List;

import androidx.lifecycle.LiveData;

public class PropositionRepository {
    private PropositionDao mPropositionDao;
    private LiveData<List<Proposition>> mAllPropositions;

    PropositionRepository(Application application) {
        ProjetRoomDatabase db = ProjetRoomDatabase.getDatabase(application);
        mPropositionDao = db.propositionDao();
        mAllPropositions = mPropositionDao.getAllProposition();
    }

    LiveData<List<Proposition>> getAllProposition() {
        return mAllPropositions;
    }

    LiveData<List<Proposition>> getmAllPropositionForACompany(Company company){
        return mPropositionDao.getAllPropositionForACompany(company.getId());
    }

    public void insert (Proposition employee) {
        new insertAsyncTask(mPropositionDao).execute(employee);
    }

    public void delete (Proposition employee) {
        new deleteAsyncTask(mPropositionDao).execute(employee);
    }

    private static class insertAsyncTask extends AsyncTask<Proposition, Void, Void> {

        private PropositionDao mAsyncTaskDao;

        insertAsyncTask(PropositionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Proposition... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Proposition, Void, Void> {

        private PropositionDao mAsyncTaskDao;

        deleteAsyncTask(PropositionDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Proposition... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
