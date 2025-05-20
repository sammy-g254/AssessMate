package com.sammyg.assessmate.ui.theme.screens.management.students

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.sammyg.assessmate.ui.theme.screens.management.students.cards.CurrentAssignmentDialog
import com.sammyg.assessmate.ui.theme.GlobalTopNavBar
import com.sammyg.assessmate.ui.theme.screens.management.global.cards.ManagementCard
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import com.sammyg.assessmate.data.database.SessionManager


@Composable
fun CurrentAssignments(
    navController: NavHostController,
    assignmentViewModel: AssignmentViewModel,
    authViewModel: UserAuthViewModel
) {
    var selected by remember { mutableStateOf<Assignment?>(null) }
    var currentSchoolName by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(selected) {
        if (selected != null) {
            SessionManager.fetchCurrentUserSchoolName { name ->
                currentSchoolName = name
            }
        }
    }

    LaunchedEffect(assignmentViewModel.assignments.size) {
        Log.d("CurrentAssignments", "Assignments count: ${assignmentViewModel.assignments.size}")
    }

    Scaffold(
        topBar = {
            GlobalTopNavBar(
                title = "Current Assignments",
                onClose = { navController.popBackStack() }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .paint(
                    painter = painterResource(R.drawable.img2),
                    contentScale = ContentScale.Crop
                )
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
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

            // Show details dialog when an assignment is selected
            if (selected != null && currentSchoolName != null) {
                CurrentAssignmentDialog(
                    schoolName = currentSchoolName!!,
                    assigntitle = selected!!.assigntitle,
                    authViewModel = authViewModel,
                    assignId = selected!!.assignId,
                    assigndescription = selected!!.assigndescription,
                    createdTime = selected!!.createdTime,
                    dueDate = selected!!.dueDate,
                    teacher = selected!!.teacher,
                    className = selected!!.className,
                    fileURL = selected!!.fileURL,
                    onClose = { selected = null }
                )
            }
        }
    }
}






