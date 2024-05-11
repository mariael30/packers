package org.d3if3137.packers.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PacksDao {

    @Insert
    suspend fun insert(packs: Packs)

    @Update
    suspend fun update(packs: Packs)
    @Delete
    suspend fun delete(packs: Packs)

    @Query("SELECT * FROM packs ORDER BY judul")
    fun getPacks(): Flow<List<Packs>>

    @Query("SELECT * FROM packs WHERE id = :id")
    suspend fun getPacksById(id:Long): Packs?
}