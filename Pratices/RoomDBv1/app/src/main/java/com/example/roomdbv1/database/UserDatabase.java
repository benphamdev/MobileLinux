package com.example.roomdbv1.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomdbv1.User;

@Database(
        entities = {User.class},
        version = 2
)
public abstract class UserDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "user.db";
    static Migration migration_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE users ADD COLUMN dob TEXT");
        }
    };
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                                   context.getApplicationContext(),
                                   UserDatabase.class,
                                   DATABASE_NAME
                           )
                           .allowMainThreadQueries()
                           .addMigrations(migration_1_2)
                           .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();
}
