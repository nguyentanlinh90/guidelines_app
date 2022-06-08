package com.ntl.guidelinesapp.modules.room_db.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.Room
import androidx.room.migration.Migration
import com.ntl.guidelinesapp.modules.room_db.User

//increase version when migration
@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false
) //todo @TypeConverters(DateConverter.class)
abstract class UserDatabase : RoomDatabase() {

    abstract fun getUserDAO(): UserDAO?

    companion object {
        private const val DATABASE_NAME = "user.db"

        //Them cot vao bang: ALTER TABLE [ten_bang] ADD COLUMN [ten_cot] [kieu_cot]
        //Them nhieu cot vao bang: ALTER TABLE [ten_bang] ADD [cot_1] [kieu_cot_1], [cot_2] [kieu_cot_2]...
        //Edit kieu du lieu cot trong bang: ALTER TABLE [ten_bang] ALTER COLUMN [ten_cot] [kieu_cot]
        //Xoa cot trong bang: ALTER TABLE [ten_bang] DROP COLUMN [ten_ccot]
        // migrate lan 1
        var migration_v1_to_v2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD year TEXT")
            }
        }

        // migrate lan 2
        var migration_v2_to_v3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD phone TEXT")
            }
        }

        // as follow Singleton Patten
        private var instance: UserDatabase? = null

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): UserDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    DATABASE_NAME
                )
                    .allowMainThreadQueries() //                    .addMigrations(migration_v1_to_v2) //migration lan 1
                    //                    .addMigrations(migration_v2_to_v3) //migration lan 2
                    .build()
            }
            return instance
        }
    }
}