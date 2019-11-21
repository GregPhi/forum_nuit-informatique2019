package com.example.forumnuitdelinformatique.dao;

import com.example.forumnuitdelinformatique.objet.Proposition;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface PropositionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Proposition proposition);

    @Update
    void update(Proposition proposition);

    @Query("DELETE FROM proposition_table")
    void deleteAll();

    @Query("SELECT * FROM proposition_table")
    List<Proposition> getPropositionList();

    @Query("SELECT * FROM proposition_table")
    LiveData<List<Proposition>> getAllProposition();

    @Query("SELECT * FROM proposition_table WHERE company_id =:id")
    LiveData<List<Proposition>>  getAllPropositionForACompany(int id);

    @Delete
    void delete(Proposition proposition);
}
