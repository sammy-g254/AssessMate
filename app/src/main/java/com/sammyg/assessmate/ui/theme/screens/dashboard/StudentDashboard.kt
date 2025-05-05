package com.sammyg.assessmate.ui.theme.screens.dashboard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sammyg.assessmate.R


@Composable
fun StudentDashboard(){
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
            description1 = "View your new assignments",
            onClick = {},
        )
        DashboardCard(
            title1 = "MANAGE",
            description1 = "Manage your current assignments",
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
fun StudentDashboardPreview(){
    StudentDashboard()
}