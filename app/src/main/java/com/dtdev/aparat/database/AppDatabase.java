package com.dtdev.aparat.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.dtdev.aparat.models.Video;

@Database(entities = {Video.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract IDAO idao();

    private static AppDatabase instance = null;


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context , AppDatabase.class , "videoDatabase")
                    .allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

}
