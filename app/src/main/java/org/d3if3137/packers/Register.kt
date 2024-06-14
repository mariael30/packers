package org.d3if3137.packers

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.d3if3137.packers.ui.theme.PackersTheme

@Composable
fun Register(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = stringResource(R.string.register),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(text = stringResource(R.string.input_username))
                })

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(text = stringResource(R.string.input_password))
                }
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = {
                    Text(text = stringResource(R.string.verify_password))
                })

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.register))
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Preview(showBackground = true)
@Composable
fun PreviewRegister() {
    PackersTheme {
        Register()
    }
}