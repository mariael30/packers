package org.d3if3137.packers.model

import androidx.compose.ui.graphics.vector.ImageVector
import org.d3if3137.packers.R

enum class NavigationItem(
    val title: String,
    val icon: Int
    ){
    Electricity(
        icon = R.drawable.baseline_bolt_24,
        title = "Electricity"
    ),
    Emergency(
        icon = R.drawable.baseline_emergency_24,
        title = "Emergency"
    ),
    Amenties(
        icon = R.drawable.baseline_shower_24,
        title = "Amenties"
    )
}