package com.sammyg.assessmate.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.navigation.ROUT_ACCESS_ALL_ASSIGNMENTS
import com.sammyg.assessmate.navigation.ROUT_STUDENT_MANAGE_ASSIGNMENTS
import com.sammyg.assessmate.navigation.ROUT_STUDENT_NEW_ASSIGNMENTS
import com.sammyg.assessmate.ui.theme.screens.dashboard.styling.DashboardCard
import com.sammyg.assessmate.ui.theme.screens.dashboard.styling.TopDashboardNavBar


@Composable
fun StudentDashboard(navController: NavHostController, userAuthViewModel: UserAuthViewModel) {

    val user = userAuthViewModel.currentUserData.value
    val userName = user?.name ?: "Teacher"

    Scaffold(
        topBar = {
            TopDashboardNavBar(title = "Student's Dashboard")
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "WELCOME BACK, ${userName.uppercase()}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color(red = 103, green = 58, blue = 183, alpha = 255),
            )
            //Add cards
            DashboardCard(
                title1 = "NEW",
                description1 = "Access your new assignments",
                onClick = {navController.navigate(ROUT_STUDENT_NEW_ASSIGNMENTS)},
            )
            DashboardCard(
                title1 = "MANAGE",
                description1 = "Manage your current assignments",
                onClick = {navController.navigate(ROUT_STUDENT_MANAGE_ASSIGNMENTS)},
            )
            DashboardCard(
                title1 = "REVISION",
                description1 = "Access all assignments",
                onClick = {navController.navigate(ROUT_ACCESS_ALL_ASSIGNMENTS)},
            )

        }
    }
}





@Preview(showBackground = true)
@Composable
fun StudentDashboardPreview(){
    StudentDashboard(navController = rememberNavController(), userAuthViewModel = UserAuthViewModel(navController = rememberNavController(), context = LocalContext.current))
}