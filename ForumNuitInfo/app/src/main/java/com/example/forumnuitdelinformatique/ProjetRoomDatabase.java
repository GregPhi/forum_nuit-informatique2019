package com.example.forumnuitdelinformatique;

import android.content.Context;
import android.os.AsyncTask;

import com.example.forumnuitdelinformatique.dao.CompanyDao;
import com.example.forumnuitdelinformatique.dao.EmployeeDao;
import com.example.forumnuitdelinformatique.dao.PropositionDao;
import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;
import com.example.forumnuitdelinformatique.objet.Proposition;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Company.class, Employee.class, Proposition.class}, version = 1)
public abstract class ProjetRoomDatabase extends RoomDatabase{
    public abstract CompanyDao companyDao();
    public abstract EmployeeDao employeeDao();
    public abstract PropositionDao propositionDao();

    private static volatile ProjetRoomDatabase INSTANCE;

    public static ProjetRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjetRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProjetRoomDatabase.class, "forum_database")
                            .addCallback(sRoomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CompanyDao mCompanyDao;
        private final EmployeeDao mEmployeeDao;
        private final PropositionDao mPropositionDao;

        PopulateDbAsync(ProjetRoomDatabase db) {
            mCompanyDao = db.companyDao();
            mEmployeeDao = db.employeeDao();
            mPropositionDao = db.propositionDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mPropositionDao.deleteAll();
            mEmployeeDao.deleteAll();
            mCompanyDao.deleteAll();

            Company c1 = new Company();
            c1.setId(1);
            c1.setNom("Norsys");
            c1.setDescription("Description\nTest");
            c1.setLogo(R.drawable.logo_norsys);
            mCompanyDao.insert(c1);

            Company c2 = new Company();
            c2.setId(2);
            c2.setNom("Smile");
            c2.setDescription("Description\nTest");
            c2.setLogo(R.drawable.logo_smile);
            mCompanyDao.insert(c2);

            Company c3 = new Company();
            c3.setId(3);
            c3.setNom("Alten");
            c3.setDescription("Description\nTest");
            c3.setLogo(R.drawable.logo_alten);
            mCompanyDao.insert(c3);

            Employee e1 = new Employee();
            e1.setCompanyId(c1.getId());
            e1.setNom("Nom test");
            e1.setPrenom("Prenom test");
            e1.setMail("test@test.fr");
            e1.setPoste("Dev java");
            mEmployeeDao.insert(e1);
            Employee e2 = new Employee();
            e2.setCompanyId(c1.getId());
            e2.setNom("Nom test");
            e2.setPrenom("Prenom test");
            e2.setMail("test@test.fr");
            e2.setPoste("RH");
            mEmployeeDao.insert(e2);

            Proposition p1 = new Proposition();
            p1.setCompanyId(c1.getId());
            p1.setDescription("Création logiciel pour afficher Hello World");
            p1.setForm_search("L3 Info");
            p1.setNom("Développeur Java");
            mPropositionDao.insert(p1);
            Proposition p2 = new Proposition();
            p2.setCompanyId(c1.getId());
            p2.setDescription("Création site");
            p2.setForm_search("L2 Info");
            p2.setNom("Développeur HTML");
            mPropositionDao.insert(p2);

            return null;
        }
    }
}
