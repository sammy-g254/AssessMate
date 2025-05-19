package com.sammyg.assessmate.ui.theme.screens.management.teachers

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.AssignmentViewModel
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.Purple
import com.sammyg.assessmate.ui.theme.screens.management.global.cards.DownloadAssignmentDetails
import com.sammyg.assessmate.ui.theme.screens.management.teachers.cards.TeacherManagementCard
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCreatedAssignments(
    navController: NavHostController,
    assignmentViewModel: AssignmentViewModel,
    authViewModel: UserAuthViewModel
) {
    var selected by remember { mutableStateOf<Assignment?>(null) }
    var selectedToDelete by remember { mutableStateOf<Assignment?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Created Assignments", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
        // 1️⃣ Scrollable list of teacher's own assignments
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
            items(assignmentViewModel.createdAssignments) { assignment ->
                TeacherManagementCard(
                    assignment = assignment,
                    navController = navController,
                    onReadClick   = { selected = assignment },
                    onDeleteClick = { selectedToDelete = assignment }
                )
            }
        }

        // 2️⃣ Read / Download dialog
        selected?.let { a ->
            DownloadAssignmentDetails(
                assigntitle      = a.assigntitle,
                assigndescription = a.assigndescription,
                createdTime      = a.createdTime,
                dueDate          = a.dueDate,
                teacher          = a.teacher,
                className        = a.className,
                fileURL          = a.fileURL,
                context          = LocalContext.current,
                onClose          = { selected = null }
            )
        }

        // 3️⃣ Delete confirmation dialog
        selectedToDelete?.let { toDelete ->
            DeleteConfirmationDialog(
                assignmentTitle = toDelete.assigntitle,
                onConfirm = {
                    assignmentViewModel.deleteAssignment(toDelete.assignId)
                    selectedToDelete = null
                },
                onDismiss = { selectedToDelete = null }
            )
        }
    }
}


@Composable
fun DeleteConfirmationDialog(
    assignmentTitle: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Assignment") },
        text = { Text("Are you sure you want to delete \"$assignmentTitle\"? This cannot be undone.") },
        confirmButton = {
            TextButton(onClick = onConfirm) {
                Text("Delete", color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun ManageCreatedAssignmentsPreview(){
    ManageCreatedAssignments(navController = rememberNavController(),
        assignmentViewModel = AssignmentViewModel(rememberNavController(), LocalContext.current, UserAuthViewModel(rememberNavController(), LocalContext.current)),
        authViewModel = UserAuthViewModel(rememberNavController(), LocalContext.current)
    )
}