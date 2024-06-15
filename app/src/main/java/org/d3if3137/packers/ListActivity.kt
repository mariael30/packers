package org.d3if3137.packers

import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3137.packers.database.Packs
import org.d3if3137.packers.database.PacksDb
import org.d3if3137.packers.model.CustomDrawerState
import org.d3if3137.packers.model.NavigationItem
import org.d3if3137.packers.model.isOpened
import org.d3if3137.packers.model.opposite
import org.d3if3137.packers.ui.theme.PackersTheme
import org.d3if3137.packers.util.coloredShadow
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    drawerState: CustomDrawerState,
    onDrawerClick: (CustomDrawerState) -> Unit
) {
    var showList by remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier.clickable(enabled = drawerState == CustomDrawerState.Opened) {
            onDrawerClick(CustomDrawerState.Closed)
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = stringResource(id = R.string.app_name2))
                },
                navigationIcon = {
                    IconButton(onClick = { onDrawerClick(drawerState.opposite()) }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Icon"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.FormBarang.route) // Ensure this route exists
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_barang),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { padding ->
        ScreenContent(showList, Modifier.padding(padding), navController)
    }
}

@Composable
fun ScreenContent(showList: Boolean, modifier: Modifier, navController: NavController) {
    val context = LocalContext.current
    val db = PacksDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()
    var drawerState by remember { mutableStateOf(CustomDrawerState.Closed) }
    var selectedNavigationItem by remember { mutableStateOf(NavigationItem.Electricity) }

    val configuration = LocalConfiguration.current
    val density = LocalDensity.current.density

    val screenWidth = remember {
        derivedStateOf { (configuration.screenWidthDp * density).roundToInt() }
    }
    val offsetValue by remember { derivedStateOf { (screenWidth.value / 4.5).dp } }
    val animatedOffset by animateDpAsState(
        targetValue = if (drawerState.isOpened()) offsetValue else 0.dp,
        label = "Animated Offset"
    )
    val animatedScale by animateFloatAsState(
        targetValue = if (drawerState.isOpened()) 0.9f else 1f,
        label = "Animated Scale"
    )

    BackHandler(enabled = drawerState.isOpened()) {
        drawerState = CustomDrawerState.Closed
    }

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize()
    ) {
        CustomDrawer(
            selectedNavigationItem = selectedNavigationItem,
            onNavigationItemClick = {
                selectedNavigationItem = it
            },
            onCloseClick = { drawerState = CustomDrawerState.Closed }
        )
        Box(
            modifier = Modifier
                .offset(x = animatedOffset)
                .scale(scale = animatedScale)
                .coloredShadow(
                    color = Color.Black,
                    alpha = 0.1f,
                    shadowRadius = 50.dp
                )
        ) {
            if (data.isEmpty()) {
                Column(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(R.string.list_kosong))
                }
            } else {
                LazyColumn(
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 84.dp)
                ) {
                    items(data) {
                        ListItem(packs = it)
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(packs: Packs) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = packs.judul,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = packs.isi,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}


//    if (data.isEmpty()) {
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Text(text = stringResource(R.string.list_kosong))
//        }
//
//    } else {
//            LazyColumn(
//                modifier = modifier.fillMaxSize(),
//                contentPadding = PaddingValues(bottom = 84.dp)
//            ) {
//                items(data) {
//                    ListItem(packs = it)
//                    Divider()
//                }
//            }
//    }

//@Composable
//fun GridItem(packs: Packs, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable { onClick() },
//        colors = CardDefaults.cardColors(
//            containerColor = MaterialTheme.colorScheme.surface,
//        ),
//        border = BorderStroke(1.dp, Color.Gray)
//    ) {
//        Column (
//            modifier = Modifier.padding(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//        ) {
//            Text(
//                text = packs.judul,
//                maxLines = 2,
//                overflow = TextOverflow.Ellipsis,
//                fontWeight = FontWeight.Bold
//            )
//            Text(
//                text = packs.isi,
//                maxLines = 4,
//                overflow = TextOverflow.Ellipsis,
//            )
//        }
//    }
//}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview
@Composable
fun PreviewListActivity() {
    PackersTheme {
        Surface {
            ListScreen(
                navController = rememberNavController(),
                drawerState = CustomDrawerState.Closed,
                onDrawerClick = {}
            )
        }
    }
}