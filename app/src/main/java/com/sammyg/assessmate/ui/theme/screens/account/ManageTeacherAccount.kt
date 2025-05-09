package com.sammyg.assessmate.ui.theme.screens.account

import android.hardware.camera2.params.BlackLevelPattern
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sammyg.assessmate.R
import com.sammyg.assessmate.ui.theme.screens.account.styling.TopAccountNavBar
import com.sammyg.assessmate.ui.theme.screens.dashboard.SchoolDashboard
import com.sammyg.assessmate.ui.theme.screens.dashboard.styling.TopDashboardNavBar
import com.sammyg.assessmate.ui.theme.transWhite

@Composable
fun ManageTeacherAccount() {
    Scaffold(
        topBar = {
            TopAccountNavBar(title = "Manage Your Account")
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

            Text(
                text = "*Teacher NAME*",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = Color(red = 103, green = 58, blue = 183, alpha = 255),
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {},
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = transWhite,
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Edit Account Details",
                    fontSize = 16.sp
                )
            }

            Button(
                onClick = {},
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = transWhite,
                    contentColor = Color.DarkGray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(vertical = 4.dp)
            ) {
                Text(
                    text = "Logout",
                    fontSize = 16.sp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ManageTeacherAccountPreview(){
    ManageTeacherAccount()
}