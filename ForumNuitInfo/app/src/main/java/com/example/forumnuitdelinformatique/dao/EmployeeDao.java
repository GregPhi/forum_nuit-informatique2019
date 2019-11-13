package com.example.forumnuitdelinformatique.dao;

import com.example.forumnuitdelinformatique.objet.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Query("DELETE FROM employee_table")
    void deleteAll();

    @Query("SELECT * FROM employee_table")
    List<Employee> getEmployees();

    @Query("SELECT * FROM employee_table")
    LiveData<List<Employee>> getAllEmployee();

    @Query("SELECT * FROM employee_table WHERE company_id =:id")
    LiveData<List<Employee>>  getAllEmployeeForACompany(int id);

    @Delete
    void delete(Employee employee);
}
