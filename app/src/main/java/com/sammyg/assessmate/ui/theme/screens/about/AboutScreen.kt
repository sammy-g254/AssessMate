package com.sammyg.assessmate.ui.theme.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.ui.theme.GlobalTopNavBar
import com.sammyg.assessmate.ui.theme.Purple

@Composable
fun About(
    navController: NavController
) {
    Scaffold(
        topBar = {
            GlobalTopNavBar(
                title = "About",
                onClose = {navController.popBackStack()}
                    )

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .paint(
                    painter = painterResource(R.drawable.img2),
                    contentScale = ContentScale.Crop
                )
                .fillMaxSize()
                .padding(paddingValues)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .height(50.dp)
                    .background(Color.White.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AssessMate",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.DarkGray,
                )

            }

            Spacer(modifier = Modifier.height(20.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Welcome to AssessMate, an app tailored for ease of interaction between teachers and students " +
                            "when it comes to assignments.",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "This is an app which allows teachers to create and upload their" +
                            " assignments. Students in turn get to access the assignments and once done, they submit them. Teachers then get" +
                            " the chance to access the submitted assignments from the students for review, where they in turn " +
                            " upload the results for the students to access.",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }

            Spacer(modifier = Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text =  "I hope you enjoy the services provided by AssessMate. Thank you for choosing us.",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }
            Spacer(modifier = Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text =  "This would not have been possible without the eMobilis team, which has encouraged me to be able to" +
                            " create this app. I appreciate for their assistance and support.",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Color.DarkGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }
            Spacer(modifier = Modifier.height(10.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White.copy(alpha = 0.7f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text =  "© 2025 SammygCorps. All rights reserved. \n" +
                            "Email: gsam65303@gmail.com",
                    fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif,
                    color = Purple,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun AboutPreview(){
    About(navController = rememberNavController())
}