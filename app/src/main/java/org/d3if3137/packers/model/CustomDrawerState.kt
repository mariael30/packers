package org.d3if3137.packers.model

enum class CustomDrawerState {
    Opened,
    Closed
}

fun CustomDrawerState.isOpened(): Boolean{
    return this.name == "Opened"
}

fun CustomDrawerState.opposite(): CustomDrawerState {
    return if (this == CustomDrawerState.Closed) CustomDrawerState.Opened
    else CustomDrawerState.Closed
}
