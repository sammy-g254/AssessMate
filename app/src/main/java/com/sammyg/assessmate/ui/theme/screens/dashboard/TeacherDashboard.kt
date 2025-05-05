package com.sammyg.assessmate.ui.theme.screens.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sammyg.assessmate.R

@Composable
fun TeacherDashboard(){
    Column (
        modifier = Modifier
            .paint(
                painter = painterResource(R.drawable.img2),
                contentScale = ContentScale.Crop
            )
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(
            text = "WELCOME BACK, /searchhow",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color(red = 103, green = 58, blue = 183, alpha = 255),
        )
        //Add cards
        DashboardCard(
            title1 = "NEW",
            description1 = "Upload your new created assignments",
            onClick = {},
        )
        DashboardCard(
            title1 = "MANAGE",
            description1 = "Manage currently submitted assignments",
            onClick = {},
        )
        DashboardCard(
            title1 = "REVISION",
            description1 = "View previously uploaded assignments",
            onClick = {},
        )

    }
}





@Preview(showBackground = true)
@Composable
fun TeacherDashboardPreview(){
    TeacherDashboard()
}