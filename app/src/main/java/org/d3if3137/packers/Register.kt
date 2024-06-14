package org.d3if3137.packers

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3137.packers.datastore.StoreRegister
import org.d3if3137.packers.ui.theme.PackersTheme


@Composable
fun Register(navController: NavController){
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreRegister(context)
    val keyboardController = LocalSoftwareKeyboardController.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var verifiyPassword by remember { mutableStateOf("") }
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
        ) {
            Text(
                text = stringResource(R.string.register),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 80.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = username,
                    onValueChange = {username = it},
                    label = {
                        Text(text = stringResource(R.string.input_username))
                    })

                OutlinedTextField(
                    value = password,
                    onValueChange = {password = it},
                    label = {
                        Text(text = stringResource(R.string.input_password))
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                OutlinedTextField(
                    value = verifiyPassword,
                    onValueChange = {verifiyPassword = it},
                    label = {
                        Text(text = stringResource(R.string.verify_password))
                    },
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = {
                              scope.launch {
                                  dataStore.saveUsername(username)
                                  dataStore.savePassword(password)
                                  navController.navigate(Screen.Home.route)
                                  keyboardController?.hide()
                              }
                    },
                    modifier = Modifier
                        .width(280.dp)
                        .height(56.dp)
                ){
                    Text(text = stringResource(id = R.string.register))
                }
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
fun PreviewRegister() {
    PackersTheme {
        Register(rememberNavController())
    }
}