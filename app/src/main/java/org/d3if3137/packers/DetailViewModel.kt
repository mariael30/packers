package org.d3if3137.packers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3137.packers.database.Packs
import org.d3if3137.packers.database.PacksDao

class DetailViewModel(private val dao: PacksDao): ViewModel() {

    fun insert(judul: String, isi: String) {
        val packs = Packs(
            judul = judul,
            isi = isi
        )

        viewModelScope.launch(Dispatchers.IO){
            dao.insert(packs)
        }
    }

        suspend fun getPacks(id: Long): Packs? {
            return dao.getPacksById(id)
    }

    fun update(judul: String, isi: String) {
        val packs = Packs(
            judul = judul,
            isi = isi
        )

        viewModelScope.launch(Dispatchers.IO){
            dao.update(packs)
        }
    }
}

