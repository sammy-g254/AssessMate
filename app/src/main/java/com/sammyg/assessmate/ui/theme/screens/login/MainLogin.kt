package com.sammyg.assessmate.ui.theme.screens.login

import android.annotation.SuppressLint
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.navigation.ROUT_MAIN_REGISTER
import com.sammyg.assessmate.navigation.ROUT_SCHOOL_LOGIN
import com.sammyg.assessmate.navigation.ROUT_SCHOOL_REGISTER


@SuppressLint("SuspiciousIndentation")
@Composable
fun MainLogin(
        navController: NavHostController,
        authViewModel: UserAuthViewModel
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
            Spacer(modifier = Modifier.height(30.dp))



            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Welcome Back",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive,
                color = Color(red = 103, green = 58, blue = 183, alpha = 255),
            )
            Spacer(modifier = Modifier.height(10.dp))

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email Address", fontFamily = FontFamily.SansSerif) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(5.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))


            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password", fontFamily = FontFamily.SansSerif) },
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
                onClick = { authViewModel.mainsignin(email, password) },
                colors = ButtonDefaults.buttonColors(
                    Color(
                        red = 103,
                        green = 58,
                        blue = 183,
                        alpha = 255
                    )
                ),
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





            Button(
                onClick = { navController.navigate(ROUT_MAIN_REGISTER) },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Are you a new user?",
                    fontFamily = FontFamily.SansSerif,
                    color = Color(red = 63, green = 81, blue = 181, alpha = 255),
                    fontSize = 16.sp
                )
                Text(
                    text = " Register here",
                    fontFamily = FontFamily.SansSerif,
                    color = Color(red = 103, green = 58, blue = 183, alpha = 255),
                    fontSize = 16.sp
                )
            }



            Button(
                onClick = { navController.navigate(ROUT_SCHOOL_REGISTER) },
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp),
                shape = RoundedCornerShape(5.dp),
            ) {
                Text(
                    text = "Wish to manage your school? Click here",
                    fontFamily = FontFamily.SansSerif,
                    color = Color(red = 0, green = 0, blue = 0, alpha = 255),
                    fontSize = 16.sp
                )
            }
        }
}








@Composable
@Preview(showBackground = true)
fun MainLoginPreview(){
    MainLogin(navController = rememberNavController(), authViewModel = UserAuthViewModel(navController = rememberNavController(), context = LocalContext.current))
}