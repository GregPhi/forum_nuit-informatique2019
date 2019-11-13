package com.example.forumnuitdelinformatique;

import android.content.Context;
import android.os.AsyncTask;

import com.example.forumnuitdelinformatique.dao.CompanyDao;
import com.example.forumnuitdelinformatique.dao.EmployeeDao;
import com.example.forumnuitdelinformatique.objet.Company;
import com.example.forumnuitdelinformatique.objet.Employee;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Company.class, Employee.class}, version = 1)
public abstract class ProjetRoomDatabase extends RoomDatabase{
    public abstract CompanyDao companyDao();
    public abstract EmployeeDao employeeDao();

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

        PopulateDbAsync(ProjetRoomDatabase db) {
            mCompanyDao = db.companyDao();
            mEmployeeDao = db.employeeDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mEmployeeDao.deleteAll();
            mCompanyDao.deleteAll();

            Company c1 = new Company();
            c1.setId(1);
            c1.setNom("Entreprise test");
            c1.setDescription("Description\nTest");
            c1.setPostes("Offres postes :\n-dev java\n-dev php");
            mCompanyDao.insert(c1);

            Employee e1 = new Employee();
            e1.setCompanyId(c1.getId());
            e1.setNom("Nom test");
            e1.setPrenom("Prenom test");
            e1.setMail("test@test.fr");
            e1.setPoste("Dev java");
            mEmployeeDao.insert(e1);
            return null;
        }
    }
}
