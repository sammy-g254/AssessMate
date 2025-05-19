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
import com.sammyg.assessmate.data.auth.SchoolAuthViewModel
import com.sammyg.assessmate.navigation.ROUT_ABOUT
import com.sammyg.assessmate.navigation.ROUT_MANAGE_SCHOOL_ACCOUNT


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolDashboard(
    navController: NavHostController,
    schoolAuthViewModel: SchoolAuthViewModel
) {

    val school = schoolAuthViewModel.currentSchoolData.value
    val schoolName = school?.schoolname ?: "School"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "School Info",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                actions = {
                    IconButton(onClick = {navController.navigate(ROUT_MANAGE_SCHOOL_ACCOUNT)}) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Manage Account")
                    }
                    IconButton(onClick = {navController.navigate(ROUT_ABOUT)}) {
                        Icon(Icons.Default.Info, contentDescription = "About")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF673AB7),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "${schoolName.uppercase()} ",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color(red = 103, green = 58, blue = 183, alpha = 255),
            )


            //Display school information here


        }
    }
}





@Preview(showBackground = true)
@Composable
fun SchoolDashboardPreview(){
    SchoolDashboard(navController = rememberNavController(), schoolAuthViewModel = SchoolAuthViewModel(navController = rememberNavController(), context = LocalContext.current))
}