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

@Database(entities = {Company.class, Employee.class, Proposition.class}, version = 2)
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

            Company norsys = new Company();
            norsys.setId(1);
            norsys.setNom("Norsys");
            String descript = "Norsys est une ESN engagée de plus de 500 easymakers (collaborateurs), créée en 1994, spécialisée dans le conseil en assistance à maîtrise d’ouvrage et l’ingénierie informatique sur-mesure utilisant les dernières technologies  (JAVA EE, PHP, Angular, Nodejs...). \nEn 2019, norsys a été certifiée B Corp, ce qui démontre la réalité de son engagement sur le plan social et environnemental et intègre le cercle pionnier d'entreprises internationales très impliquées. \n";
            norsys.setDescription(descript);
            norsys.setLogo(R.drawable.logo_norsys);
            mCompanyDao.insert(norsys);

            Employee e1_norsys = new Employee();
            e1_norsys.setPrenom("Mélanie");
            e1_norsys.setNom("Wilfart");
            e1_norsys.setCompanyId(norsys.getId());
            Employee e2_norsys = new Employee();
            e2_norsys.setPrenom("Denis");
            e2_norsys.setNom("Cassoret");
            e2_norsys.setCompanyId(norsys.getId());
            mEmployeeDao.insert(e1_norsys);
            mEmployeeDao.insert(e2_norsys);

            Company smile = new Company();
            smile.setId(2);
            smile.setNom("Smile");
            smile.setDescription("Description\nTest");
            smile.setLogo(R.drawable.logo_smile);
            mCompanyDao.insert(smile);

            Company alten = new Company();
            alten.setId(3);
            alten.setNom("Alten");
            alten.setDescription("Description\nTest");
            alten.setLogo(R.drawable.logo_alten);
            mCompanyDao.insert(alten);

            Company leroy_merlin = new Company();
            leroy_merlin.setId(4);
            leroy_merlin.setNom("Leroy Merlin");
            leroy_merlin.setDescription("Description\nTest");
            leroy_merlin.setLogo(R.drawable.logo_leroymerlin);
            mCompanyDao.insert(leroy_merlin);

            Employee e1_leroy_merlin = new Employee();
            e1_leroy_merlin.setCompanyId(leroy_merlin.getId());
            e1_leroy_merlin.setNom("Philippot");
            e1_leroy_merlin.setPrenom("Ingrid");
            e1_leroy_merlin.setMail("ingrid.philippot@leroymerlin.fr");
            e1_leroy_merlin.setPoste("Responsable recrutement");
            Employee e2_leroy_merlin = new Employee();
            e2_leroy_merlin.setCompanyId(leroy_merlin.getId());
            e2_leroy_merlin.setNom("Kusper");
            e2_leroy_merlin.setPrenom("Michael");
            e2_leroy_merlin.setMail("michael.kusper@leroymerlin.fr");
            e2_leroy_merlin.setPoste("Responsable pôle data");
            Employee e3_leroy_merlin = new Employee();
            e3_leroy_merlin.setCompanyId(leroy_merlin.getId());
            e3_leroy_merlin.setPrenom("Mathilde");
            e3_leroy_merlin.setNom("Thomas");
            e3_leroy_merlin.setMail("mathilde.thomas@leroymerlin.fr");
            e3_leroy_merlin.setPoste("Chargée de communication");
            Employee e4_leroy_merlin = new Employee();
            e4_leroy_merlin.setCompanyId(leroy_merlin.getId());
            e4_leroy_merlin.setNom("");
            e4_leroy_merlin.setPrenom("");
            e4_leroy_merlin.setMail("");
            e4_leroy_merlin.setPoste("");
            mEmployeeDao.insert(e1_leroy_merlin);
            mEmployeeDao.insert(e2_leroy_merlin);
            mEmployeeDao.insert(e3_leroy_merlin);
            //mEmployeeDao.insert(e4_leroy_merlin);

            Company davidson = new Company();
            davidson.setId(5);
            davidson.setNom("Davidson");
            davidson.setDescription("Passionné(e) du monde IT ? Si tu as envie d’explorer les possibilités qui s’ouvrent à toi, viens nous rencontrer !\n Plus qu’une entreprise, notre objectif sera de t’accompagner et de construire ta carrière autour de tes envies : expertise technique, pilotage de projet, agile, qualité logicielle notamment… en échange on compte sur toi pour nous apporter du fun et ta joie de vivre !");
            davidson.setLogo(R.drawable.logo_davidson);
            mCompanyDao.insert(davidson);

            Employee e1_davidson = new Employee();
            e1_davidson.setCompanyId(davidson.getId());
            e1_davidson.setNom("Sansen");
            e1_davidson.setPrenom("Kevin");
            e1_davidson.setPoste("Directeur associé");
            Employee e2_davidson = new Employee();
            e2_davidson.setCompanyId(davidson.getId());
            e2_davidson.setNom("Haren");
            e2_davidson.setPrenom("Guillaume");
            e2_davidson.setPoste("Ingénieur d'affaires");
            Employee e3_davidson = new Employee();
            e3_davidson.setCompanyId(davidson.getId());
            e3_davidson.setNom("Deleplanque");
            e3_davidson.setPrenom("Dylan");
            e3_davidson.setPoste("Ingénieur d'affaires");
            mEmployeeDao.insert(e1_davidson);
            mEmployeeDao.insert(e2_davidson);
            mEmployeeDao.insert(e3_davidson);

            Company capgemini = new Company();
            capgemini.setId(6);
            capgemini.setNom("Capgemini");
            capgemini.setDescription("Description\nTest");
            capgemini.setLogo(R.drawable.logo_capgemini);
            mCompanyDao.insert(capgemini);

            return null;
        }
    }
}
