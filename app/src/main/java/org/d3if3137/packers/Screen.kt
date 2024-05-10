package org.d3if3137.packers

sealed class Screen(val route: String) {
    data object Home: Screen("Start")
    data object ListBarang: Screen("ListScreen")
    data object FormBarang: Screen("DetailList")
}