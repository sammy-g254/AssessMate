package com.sammyg.assessmate.ui.theme.screens.login

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.SchoolAuthViewModel
import com.sammyg.assessmate.data.auth.UserAuthViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun SchoolLogin(
    navController: NavHostController,
    schoolAuthViewModel: SchoolAuthViewModel
    ){
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



        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "SCHOOL MANAGEMENT LOGIN",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color(red = 103, green = 58, blue = 183, alpha = 255),
        )
        Spacer(modifier = Modifier.height(10.dp))

        var schoolemail by remember { mutableStateOf("") }
        var schoolpassword by remember { mutableStateOf("") }

        OutlinedTextField(
            value = schoolemail,
            onValueChange = {schoolemail = it},
            label = { Text(text = "School Email", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp)
        )

        Spacer(modifier = Modifier.height(10.dp))


        OutlinedTextField(
            value = schoolpassword,
            onValueChange = {schoolpassword = it},
            label = { Text(text = "Password", fontFamily = FontFamily.SansSerif)},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            shape = RoundedCornerShape(5.dp),
            visualTransformation = PasswordVisualTransformation()

        )



        Spacer(modifier = Modifier.height(10.dp))




        Button(
            onClick = { schoolAuthViewModel.schoolsignin(schoolemail, schoolpassword) },
            colors = ButtonDefaults.buttonColors(Color(red = 103, green = 58, blue = 183, alpha = 255),),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
        ) {
            Text(
                text = "LOGIN",
                fontFamily = FontFamily.SansSerif,
            )
        }



    }





}

