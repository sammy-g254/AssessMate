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
fun SchoolDashboard(){
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
            text = "SCHOOL MANAGEMENT",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            color = Color(red = 103, green = 58, blue = 183, alpha = 255),
        )
        //Add cards
        DashboardCard(
            title1 = "STUDENTS",
            description1 = "Manage your students",
            onClick = {},
        )
        DashboardCard(
            title1 = "TEACHERS",
            description1 = "Manage your teachers",
            onClick = {},
        )
        DashboardCard(
            title1 = "ASSIGNMENTS",
            description1 = "Review uploaded assignments",
            onClick = {},
        )

    }
}





@Preview(showBackground = true)
@Composable
fun SchoolDashboardPreview(){
    SchoolDashboard()
}