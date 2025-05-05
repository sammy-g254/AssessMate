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
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sammyg.assessmate.R


@SuppressLint("SuspiciousIndentation")
@Composable
fun SchoolRegister(){
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



        Text(
            text = "REGISTER YOUR SCHOOL",
            fontSize = 30.sp,
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

        var name by remember { mutableStateOf("") }
        var schoolcode by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confpassword by remember { mutableStateOf("") }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it},
            label = { Text(text = "School Name", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Home, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)

        )


        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "School Email Address", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)
        )


        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = schoolcode,
            onValueChange = { schoolcode = it},
            label = { Text(text = "School Code", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Place, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(text = "Password", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = confpassword,
            onValueChange = {confpassword = it},
            label = { Text(text = "Confirm Password", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)

        )

        Spacer(modifier = Modifier.height(10.dp))




        val context = LocalContext.current
        //val authViewModel = AuthViewModel(navController, context)
        Button(
            onClick = { /*authViewModel.signup(name, email, password,confpassword) */},
            colors = ButtonDefaults.buttonColors(Color(red = 103, green = 58, blue = 183, alpha = 255)),            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)) {
            Text(text = "REGISTER")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { },
            colors = ButtonDefaults.buttonColors(Color.Transparent),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)) {
            Text(
                text = "Already a member?",
                fontFamily = FontFamily.SansSerif,
                color = Color(red = 200, green = 200, blue = 201, alpha = 255),
                fontSize = 16.sp
            )
            Text(
                text = " Login here",
                fontFamily = FontFamily.SansSerif,
                color = Color(red = 0, green = 188, blue = 212, alpha = 255),
                fontSize = 16.sp
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun SchoolRegisterPreview(){
    SchoolRegister()
}