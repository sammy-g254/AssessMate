package com.sammyg.assessmate.ui.theme.screens.management.teachers


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.AssignmentViewModel
import com.sammyg.assessmate.data.database.SessionManager
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.Purple
import com.sammyg.assessmate.ui.theme.screens.management.teachers.cards.SubmittedAssignmentCard
import com.sammyg.assessmate.ui.theme.screens.management.teachers.cards.UploadResultsDialog
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import androidx.compose.foundation.lazy.items



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageSubmittedAssignments(
    navController: NavHostController,
    assignmentViewModel: AssignmentViewModel,
    authViewModel: UserAuthViewModel
) {
    val context = LocalContext.current
    var schoolName by remember { mutableStateOf<String?>(null) }
    var selectedAssignment by remember { mutableStateOf<Assignment?>(null) }
    var selectedUserName by remember { mutableStateOf<String?>(null) }

    // 1️⃣ Fetch current school once
    LaunchedEffect(Unit) {
        SessionManager.fetchCurrentUserSchoolName { schoolName = it }
    }

    // 2️⃣ Build flat list of (assignment, userName)
    val submissions: List<Pair<Assignment, String>> = remember(schoolName, assignmentViewModel.createdAssignments) {
        val sn = schoolName ?: return@remember emptyList()
        assignmentViewModel.createdAssignments.flatMap { assignment ->
            val snapshot = runBlocking {
                FirebaseDatabase.getInstance()
                    .getReference("$sn/Assignments/${assignment.assignId}")
                    .get()
                    .await()
            }
            snapshot.children
                .filter { it.key != "info" }               // ← drop the extra layer
                .mapNotNull { it.key }
                .map { userName -> assignment to userName }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Manage Submitted Assignments", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor        = Purple,
                    titleContentColor     = Color.White,
                    actionIconContentColor= Color.White
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .paint(painter = painterResource(R.drawable.img2), contentScale = ContentScale.Crop)
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(submissions) { (assignment, userName) ->
                SubmittedAssignmentCard(
                    assignment = assignment,
                    userName = userName,
                    navController = navController,
                    authViewModel = authViewModel,
                    onUploadClick = {
                        selectedAssignment = assignment
                        selectedUserName = userName
                    }
                )
            }
        }

        // 3️⃣ Show dialog when a submission is selected
        if (selectedAssignment != null && selectedUserName != null && schoolName != null) {
            UploadResultsDialog(
                fileUrlInitial = "",
                schoolName     = schoolName!!,
                assignId       = selectedAssignment!!.assignId,
                userName       = selectedUserName!!,
                authViewModel  = authViewModel,
                context        = context
            ) {
                selectedAssignment = null
                selectedUserName = null
            }
        }
    }
}






/*@Preview(showBackground = true)
@Composable
fun ManageSubmittedAssignmentsPreview(){
    ManageSubmittedAssignments(navController = rememberNavController())
}*/