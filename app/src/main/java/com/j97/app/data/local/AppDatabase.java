package com.j97.app.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

class Converter {
  @TypeConverter
  public long dateToLong(Date date) {
    return date.getTime();
  }

  @TypeConverter
  public Date longToDate(long time) {
    return new Date(time);
  }
}

@Database(entities = {MaterialModel.class, NodeModel.class}, version = 2, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class AppDatabase extends RoomDatabase {

  public abstract MaterialDao materialDao();
  public abstract NodeDao nodeDao();

  private static volatile AppDatabase INSTANCE;

  public static AppDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (AppDatabase.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(
              context.getApplicationContext(),
              AppDatabase.class,
              "app_db"
          ).allowMainThreadQueries().build();
        }
      }
    }
    return INSTANCE;
  }
}