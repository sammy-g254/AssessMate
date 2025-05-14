package com.sammyg.assessmate.ui.theme.screens.management.students

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.database.SessionManager
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.screens.management.styles.TopManagementNavBar
import com.sammyg.assessmate.ui.theme.screens.management.styles.cards.StudentManagementCard
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

@Composable
fun ManageCurrentAssignment(navController: NavHostController) {
    val studentAssignments = remember { mutableStateListOf<Assignment>() }

    LaunchedEffect(Unit) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        val username = currentUser?.displayName ?: currentUser?.uid ?: return@LaunchedEffect

        SessionManager.fetchCurrentUserSchoolName { schoolName ->
            if (schoolName != null) {
                val ref = FirebaseDatabase.getInstance()
                    .getReference("$schoolName/Assignments")

                ref.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        studentAssignments.clear()
                        for (assignSnap in snapshot.children) {
                            val assignId = assignSnap.key ?: continue
                            val studentNode = assignSnap.child(username)
                            val infoNode = assignSnap.child("info")

                            if (studentNode.exists()) {
                                val assignment = infoNode.getValue(Assignment::class.java)
                                if (assignment != null) {
                                    studentAssignments.add(assignment)
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Handle error if needed
                    }
                })
            }
        }
    }

    Scaffold(
        topBar = {
            TopManagementNavBar(title = "Manage Current Assignments")
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
            studentAssignments.forEach { assignment ->
                StudentManagementCard(
                    assignment = assignment,
                    onClick = {
                        // Optional: navigate to a detail screen or show a dialog
                    }
                )
            }

            if (studentAssignments.isEmpty()) {
                Text("No current assignments downloaded.", color = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}






@Preview(showBackground = true)
@Composable
fun ManageCurrentAssignmentPreview(){
    ManageCurrentAssignment(navController = rememberNavController())
}