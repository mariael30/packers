package org.d3if3137.packers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Packs::class], version = 1, exportSchema = false)
abstract class PacksDb : RoomDatabase() {
    abstract val dao: PacksDao

    companion object {
        @Volatile
        private var INSTANCE: PacksDb? = null

        fun getInstance(context: Context): PacksDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PacksDb::class.java,
                        "packs.db"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}