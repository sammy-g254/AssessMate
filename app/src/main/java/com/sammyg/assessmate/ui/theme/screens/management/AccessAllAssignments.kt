package com.sammyg.assessmate.ui.theme.screens.management


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sammyg.assessmate.R
import com.sammyg.assessmate.ui.theme.screens.management.styles.TopManagementNavBar


@Composable
fun AccessAllAssignment() {
    Scaffold(
        topBar = {
            TopManagementNavBar(title = "Access all Assignments")
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
        }
    }
}





@Preview(showBackground = true)
@Composable
fun AccessAllAssignmentPreview(){
    AccessAllAssignment()
}