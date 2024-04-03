package org.d3if3137.packers

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.AmbientContext
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import org.d3if3137.packers.ui.theme.PackersTheme

//import kotlin.coroutines.jvm.internal.CompletedContinuation.context

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackersTheme {
                // A surface container using the 'background' color from the theme
//                Box(modifier = Modifier.fillMaxSize()) {
//                    // Background image
//                    Image(
//                        painter = painterResource(R.drawable.bggg),
//                        contentDescription = null,
//                        modifier = Modifier.fillMaxSize()
//                    )
//                    Start()

//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                    Start()

//                }
            }
        }
    }
}
//    private fun getData(): List<Background> {
//        return listOf(
//            Background("", R.drawable.bggg,
//                )
//        )
//    }
//}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(content: @Composable (Modifier) -> Unit) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(id = R.string.app_name))
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    )
                )
            }
        ) { padding ->
            content(Modifier.padding(padding))
        }
    }

    @Composable
    fun Start() {
        MainScreen { modifier ->
            val context = LocalContext.current

            Box(
                modifier = modifier.fillMaxSize()
            ){
                Image(
                    painter = painterResource(id = R.drawable.bggg),
                    contentDescription = stringResource(R.string.gambar),
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.matchParentSize()
                )
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Get Ready ?!",
                    style = MaterialTheme.typography.displayMedium
                )
                Button(
                    onClick = {
                        val navigate = Intent(context, ListActivity::class.java)
                        context.startActivity(navigate)
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(top = 16.dp),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Text(text = stringResource(R.string.Start))
                }
            }
        }
    }


//gajadi masukin gambar
//@Composable
//fun Backgroundnya(background: Background) {
//    MainScreen {modifier ->
//        Column(
//            modifier = modifier
//                .fillMaxSize()
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//            Image(
//                painter = painterResource(background.imageResId),
//                contentDescription = stringResource(R.string.gambar, background.nama),
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.size(132.dp)
//            )
//        }
//    }
//}

@Composable
fun Greeting(name: String) {
    MainScreen {modifier ->
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    PackersTheme {
        Start()
    }
}