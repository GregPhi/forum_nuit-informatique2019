package com.example.forumnuitdelinformatique.dao;

import com.example.forumnuitdelinformatique.objet.Company;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CompanyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Company company);

    @Update
    void updateCompany(Company... company);

    @Query("DELETE FROM company_table")
    void deleteAll();

    @Query("SELECT * from company_table ORDER BY not(interessed)")
    LiveData<List<Company>> getAllCompany();

    @Query("SELECT * from company_table ORDER BY not(interessed)")
    List<Company> getCmpanys();

    @Delete
    void delete(Company company);
}
