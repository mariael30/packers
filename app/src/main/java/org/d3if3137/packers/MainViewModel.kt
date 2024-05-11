package org.d3if3137.packers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.d3if3137.packers.database.Packs
import org.d3if3137.packers.database.PacksDao

class MainViewModel(dao: PacksDao): ViewModel() {

    val data: StateFlow<List<Packs>> = dao.getPacks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        initialValue = emptyList()
    )

//    val data = getDataDummy()
//
//    private fun getDataDummy(): List<Packs> {
//        val data = mutableListOf<Packs>()
//        for (i in 29 downTo 20) {
//            data.add(
//                Packs(
//                    i.toLong(),
//                    "Baju",
//                    "Kemeja, Kaos, Hoodie"
//                )
//            )
//        }
//        return data
//    }
}