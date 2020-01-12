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
public interface NodeDao {
  @Delete
  void delete(NodeModel model);

  @Update
  void update(NodeModel model);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  void insert(NodeModel model);

  @Query("DELETE FROM node_models")
  void deleteAll();

  @Query("SELECT * from node_models ORDER BY created_at ASC")
  LiveData<List<NodeModel>> getAllNodeModels();

  @Query("SELECT * FROM node_models WHERE _id = :id LIMIT 1")
  NodeModel findById(int id);
}
