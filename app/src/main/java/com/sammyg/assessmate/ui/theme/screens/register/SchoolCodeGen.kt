package com.sammyg.assessmate.ui.theme.screens.register

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sammyg.assessmate.R


@SuppressLint("SuspiciousIndentation")
@Composable
fun SchoolCodeGen(){
    Column(
        modifier = Modifier
            .paint(
                painter = painterResource(R.drawable.img),
                contentScale = ContentScale.Crop
            )
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var schoolcode by remember { mutableStateOf("") }

        Text(
            text = "FINISH REGISTERING YOUR SCHOOL",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color(red = 255, green = 255, blue = 255, alpha = 255),
            modifier = Modifier
                .background(
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = schoolcode,
            onValueChange = {schoolcode = it},
            label = { Text(text = "School Code", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        val context = LocalContext.current
        //val authViewModel = AuthViewModel(navController, context)

        Button(
            onClick = { /*authViewModel.signup(name, email, password,confpassword) */},
            colors = ButtonDefaults.buttonColors(Color(red = 103, green = 58, blue = 183, alpha = 255)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)) {
            Text(text = "GENERATE SCHOOL CODE")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "The code generated above is your school code. This will be used by both students and teachers to register to your specific school.",
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color(red = 103, green = 58, blue = 183, alpha = 255),
            textAlign = TextAlign.Center,
            )

        Spacer(modifier = Modifier.height(30.dp))




        Button(
            onClick = { /*authViewModel.signup(name, email, password,confpassword) */},
            colors = ButtonDefaults.buttonColors(Color(red = 103, green = 58, blue = 183, alpha = 255)),            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)) {
            Text(text = "FINISH")
        }

        Spacer(modifier = Modifier.height(10.dp))


        }

    }


@Composable
@Preview(showBackground = true)
fun SchoolCodeGenPreview(){
    SchoolCodeGen()
}