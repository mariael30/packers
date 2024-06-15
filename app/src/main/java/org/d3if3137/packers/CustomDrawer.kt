package org.d3if3137.packers

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.d3if3137.packers.model.NavigationItem

fun CustomDrawer(
    selectedNavigationItem: NavigationItem,
    onNavigationItemClick: (NavigationItem)-> null,
    onCloseClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.6f)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        ){
            IconButton(
                onClick = onCloseClick) {
                    Icon(
                     imageVector = Icons.Filled.arrowback,
                        contentDescription = stringResource(R.string.arrow_back),
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            ) {

            }
        }
    }
}