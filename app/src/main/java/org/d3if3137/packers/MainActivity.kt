package org.d3if3137.packers

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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if3137.packers.database.PacksDb
import org.d3if3137.packers.navigation.SetupNavGraph
//import androidx.compose.ui.platform.AmbientContext
//import kotlinx.coroutines.flow.internal.NoOpContinuation.context
import org.d3if3137.packers.ui.theme.PackersTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PackersTheme {
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    SetupNavGraph()
                }
            }
        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(content: @Composable (Modifier) -> Unit) {
        Scaffold(

        ) { padding ->
            content(Modifier.padding(padding))
        }
    }

    @Composable
    fun Start(navController: NavController) {
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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(painter = painterResource(id = R.drawable.logo_packers),
                    contentDescription = stringResource(R.string.logo_login),
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = stringResource(R.string.opening),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold)

                Text(text = stringResource(R.string.login_first))

                Spacer(modifier = Modifier.height(7.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {
                    Text(text = stringResource(R.string.username))
                })

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = {
                        Text(text = stringResource(R.string.password))
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                
                Button(onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.login))
                }
                TextButton(onClick = {},
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = Color.White)
                    ){
                    Text(text = stringResource(id = R.string.dont_have_account))
                }
            }
        }
    }


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    PackersTheme {
        Start(rememberNavController())
    }
}