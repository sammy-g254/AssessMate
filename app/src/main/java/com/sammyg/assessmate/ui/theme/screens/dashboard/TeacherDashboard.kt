package com.sammyg.assessmate.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import com.sammyg.assessmate.navigation.ROUT_ABOUT
import com.sammyg.assessmate.navigation.ROUT_ACCESS_ALL_ASSIGNMENTS
import com.sammyg.assessmate.navigation.ROUT_MANAGE_USER_ACCOUNT
import com.sammyg.assessmate.navigation.ROUT_TEACHER_CREATE_ASSIGNMENTS
import com.sammyg.assessmate.navigation.ROUT_TEACHER_MANAGE_CREATED_ASSIGNMENTS
import com.sammyg.assessmate.navigation.ROUT_TEACHER_MANAGE_SUBMITTED_ASSIGNMENTS
import com.sammyg.assessmate.ui.theme.screens.dashboard.styling.DashboardCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherDashboard(
    navController: NavHostController,
    userAuthViewModel: UserAuthViewModel
) {

    val user = userAuthViewModel.currentUserData.value
    val userName = user?.name ?: "Unknown user"


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Teacher Dashboard",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(onClick = { navController.navigate(ROUT_MANAGE_USER_ACCOUNT)}) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Manage Account")
                    }
                    IconButton(onClick = { navController.navigate(ROUT_ABOUT) }) {
                        Icon(Icons.Default.Info, contentDescription = "About")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF673AB7),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .paint(
                    painter = painterResource(R.drawable.img2),
                    contentScale = ContentScale.Crop
                )
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
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
                description1 = "Create new assignments",
                onClick = {navController.navigate(ROUT_TEACHER_CREATE_ASSIGNMENTS)},
            )
            DashboardCard(
                title1 = "EDIT",
                description1 = "Manage uploaded assignments",
                onClick = {navController.navigate(ROUT_TEACHER_MANAGE_CREATED_ASSIGNMENTS)},
            )
            DashboardCard(
                title1 = "MANAGE",
                description1 = "Manage students' submitted assignments",
                onClick = {navController.navigate(ROUT_TEACHER_MANAGE_SUBMITTED_ASSIGNMENTS)},
            )
            DashboardCard(
                title1 = "REVISION",
                description1 = "Access all uploaded assignments",
                onClick = {navController.navigate(ROUT_ACCESS_ALL_ASSIGNMENTS)},
            )

        }
    }
}




@Preview(showBackground = true)
@Composable
fun TeacherDashboardPreview(){
    TeacherDashboard(navController = rememberNavController(), userAuthViewModel = UserAuthViewModel(navController = rememberNavController(), context = LocalContext.current))
}