package com.j97.app.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MaterialDao {
    @Delete
    void delete(MaterialModel model);

    @Update
    void update(MaterialModel model);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(MaterialModel model);

    @Query("DELETE FROM material_models")
    void deleteAll();

    @Query("SELECT * from material_models ORDER BY created_at ASC")
    LiveData<List<MaterialModel>> getAllMaterialModels();

    @Query("SELECT * FROM material_models WHERE _id = :id LIMIT 1")
    MaterialModel findById(int id);
}
