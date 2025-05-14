package com.sammyg.assessmate.ui.theme.screens.management.teachers


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.ui.theme.Purple


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCreatedAssignments(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Manage Submitted Assignments", fontWeight = FontWeight.Bold)
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple,
                    titleContentColor = Color.White,
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
        }
    }
}





@Preview(showBackground = true)
@Composable
fun ManageCreatedAssignmentsPreview(){
    ManageCreatedAssignments(navController = rememberNavController())
}