package com.ntl.guidelinesapp.modules.room_db.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ntl.guidelinesapp.modules.room_db.User;

//increase version when migration
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "user.db";

    //Them cot vao bang: ALTER TABLE [ten_bang] ADD COLUMN [ten_cot] [kieu_cot]
    //Them nhieu cot vao bang: ALTER TABLE [ten_bang] ADD [cot_1] [kieu_cot_1], [cot_2] [kieu_cot_2]...
    //Edit kieu du lieu cot trong bang: ALTER TABLE [ten_bang] ALTER COLUMN [ten_cot] [kieu_cot]
    //Xoa cot trong bang: ALTER TABLE [ten_bang] DROP COLUMN [ten_ccot]

    // migrate lan 1
    static Migration migration_v1_to_v2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user ADD year TEXT");
        }
    };

    // migrate lan 2
    static Migration migration_v2_to_v3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user ADD phone TEXT");
        }
    };

    // as follow Singleton Patten
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
//                    .addMigrations(migration_v1_to_v2) //migration lan 1
//                    .addMigrations(migration_v2_to_v3) //migration lan 2
                    .build();
        }
        return instance;
    }

    public abstract UserDAO userDAO();
}
