package org.d3if3137.packers.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Packs")
data class Packs(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val judul: String,
    val isi: String
)
