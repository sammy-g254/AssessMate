package com.sammyg.assessmate.ui.theme.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.navigation.ROUT_MAIN_LOGIN
import com.sammyg.assessmate.navigation.ROUT_STUDENT_DASHBOARD
import com.sammyg.assessmate.navigation.ROUT_TEACHER_DASHBOARD
import com.sammyg.assessmate.ui.theme.Purple
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    authViewModel: UserAuthViewModel
) {
    // Launch navigation logic only once
    LaunchedEffect(Unit) {
        // Keep splash on screen for 2 seconds
        delay(2000L)
            navController.navigate(ROUT_MAIN_LOGIN) {
                popUpTo(0)
            }
        }


    // UI: a full‑screen background with centered logo
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Purple)
        // or use .paint(painter = painterResource(R.drawable.splash_bg), contentScale = ContentScale.Crop)
    ) {
        Image(
            painter = painterResource(R.drawable.splash), // your logo asset
            contentDescription = "App Logo",
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit
        )
    }
}