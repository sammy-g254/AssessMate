package com.sammyg.assessmate.ui.theme.screens.management.global

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.AssignmentViewModel
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.screens.management.global.cards.DownloadAssignmentDetails
import com.sammyg.assessmate.ui.theme.GlobalTopNavBar
import com.sammyg.assessmate.ui.theme.screens.management.global.cards.ManagementCard

@Composable
fun AccessAllAssignments(
    navController: NavHostController,
    assignmentViewModel: AssignmentViewModel,
    authViewModel: UserAuthViewModel
) {
    var selected by remember { mutableStateOf<Assignment?>(null) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            GlobalTopNavBar(
                title = "All Assignments",
                onClose = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .paint(
                    painter = painterResource(R.drawable.img2),
                    contentScale = ContentScale.Crop
                )
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(assignmentViewModel.assignments) { assignment ->
                ManagementCard(
                    assignment = assignment,
                    onClick = { selected = assignment }
                )
            }
        }

        // Show download dialog on selection
        selected?.let { a ->
            DownloadAssignmentDetails(
                assigntitle = a.assigntitle,
                assigndescription = a.assigndescription,
                createdTime = a.createdTime,
                dueDate = a.dueDate,
                teacher = a.teacher,
                className = a.className,
                fileURL = a.fileURL,
                context = context,
                onClose = { selected = null }
            )
        }
    }
}





