package com.moeiny.reza.nfoxsport.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moeiny.reza.nfoxsport.database.dao.*
import com.moeiny.reza.nfoxsport.database.entitiy.*

@Database(entities = [(MatchEntity::class), (PlayerEntity::class), (StatsEntity::class),
                      (TeamEntity::class), (TopPlayerEntity::class)], version = 1, exportSchema = false)

public abstract class AppDatabase : RoomDatabase() {

    abstract fun MatchDao(): MatchDao

    abstract fun PlayerDao(): PlayerDao

    abstract fun StatsDao(): StatsDao

    abstract fun TeamDao(): TeamDao

    abstract fun TopPlayersDao(): TopPlayersDao

    companion object {

        private var instance: AppDatabase? = null
        public var DB_NAME = "LifeGaurd"

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room
                        .databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance!!
        }
    }

    private val roomCallback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            PopulateDbAsyncTask(instance)
                    .execute()
        }
    }

}

    class PopulateDbAsyncTask(db: AppDatabase?) : AsyncTask<Unit, Unit, Unit>() {
    private val MatchDao = db?.MatchDao()

    override fun doInBackground(vararg p0: Unit?) {
       // deviceDao?.insert(DeviceEntity("", ""))

      }
    }

