package com.sammyg.assessmate.ui.theme.screens.management.students


import android.content.Intent
import android.net.Uri
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
import com.sammyg.assessmate.ui.theme.screens.management.students.cards.AssignmentResultsCard
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import androidx.compose.foundation.lazy.items



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssignmentResults(
    navController: NavHostController,
    assignmentViewModel: AssignmentViewModel,
    authViewModel: UserAuthViewModel
) {
    val context = LocalContext.current
    var schoolName by remember { mutableStateOf<String?>(null) }

    var submissions by remember { mutableStateOf<List<Triple<Assignment, String, String>>>(emptyList()) }

    // Fetch the current school name once
    LaunchedEffect(Unit) {
        SessionManager.fetchCurrentUserSchoolName { sn ->
            schoolName = sn
            if (sn != null) {
                val newList = assignmentViewModel.createdAssignments.flatMap { assignment ->
                    val assignId = assignment.assignId
                    val snapshot = runBlocking {
                        FirebaseDatabase.getInstance()
                            .getReference("$sn/Assignments/$assignId")
                            .get()
                            .await()
                    }

                    snapshot.children.mapNotNull { userSnap ->
                        val userName = userSnap.key ?: return@mapNotNull null
                        val reportfileURL = userSnap.child("reportfileURL").getValue(String::class.java)
                        if (!reportfileURL.isNullOrBlank()) {
                            Triple(assignment, userName, reportfileURL)
                        } else null
                    }
                }
                submissions = newList
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Assignment Results", fontWeight = FontWeight.Bold) },
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
        LazyColumn(
            modifier = Modifier
                .paint(painterResource(R.drawable.img2), contentScale = ContentScale.Crop)
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(submissions) { (assignment, userName, reportfileURL) ->
                AssignmentResultsCard(
                    assignment = assignment,
                    userName = userName,
                    reportfileURL = reportfileURL,
                    onDownloadClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(reportfileURL))
                        context.startActivity(intent)
                    }
                )
            }
        }
    }
}





/*@Preview(showBackground = true)
@Composable
fun AssignmentResultsPreview(){
    AssignmentResults(navController = rememberNavController())
}*/