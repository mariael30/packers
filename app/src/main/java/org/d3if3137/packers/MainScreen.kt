package org.d3if3137.packers

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.d3if3137.packers.datastore.StoreRegister
import org.d3if3137.packers.ui.theme.PackersTheme


@Composable
fun MainScreen(navController: NavController) {
    PackersTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
            //add navigation drawer
        ) {
            Start(rememberNavController())
        }
    }
}

@Composable
fun Start(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = remember { StoreRegister(context) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_packers),
            contentDescription = stringResource(R.string.logo_login),
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(R.string.opening),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(text = stringResource(R.string.login_first))

        Spacer(modifier = Modifier.height(7.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text(text = stringResource(R.string.username)) }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                scope.launch {
                    val savedUsername = dataStore.getUsername.first() ?: ""
                    val savedPassword = dataStore.getPassword.first() ?: ""

                    if (username == savedUsername && password == savedPassword) {
                        navController.navigate(Screen.ListBarang.route)
                    } else {

                    }
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.login))
        }

        TextButton(
            onClick = {
                navController.navigate(Screen.FormRegister.route)
            },
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color.White
            )
        ) {
            Text(text = stringResource(id = R.string.dont_have_account))
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