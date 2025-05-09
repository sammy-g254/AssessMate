package com.sammyg.assessmate.ui.theme.screens.management.students.detailedCards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.sammyg.assessmate.ui.theme.Purple

@Composable
fun ManageAssignmentDetails(
    navController: NavHostController,
    title2: String,
    description: String,
    timeCreated: String,
    dueDate: String,
    teacherName: String,
    className: String,
    onClose: () -> Unit
) {
    Dialog(onDismissRequest = { onClose() }) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    // Close Button
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "MANAGE ASSIGNMENT",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Purple
                        )
                        IconButton(onClick = { onClose() }) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    }

                    // Content
                    Text(text = title2, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = description, fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Created: $timeCreated", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "Due: $dueDate", color = Color.Red, fontSize = 14.sp)
                    Text(text = "Teacher: $teacherName", color = Color.DarkGray, fontSize = 14.sp)
                    Text(text = "Class: $className", color = Color.DarkGray, fontSize = 14.sp)

                    Spacer(modifier = Modifier.height(24.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { /* Submit action */ }) {
                            Text("Submit")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { /* Download action */ }) {
                            Text("Download Assignment")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = { /* Download action */ }) {
                            Text("Download Results")
                        }
                    }
                }
            }
        }
    }
}

/*var showDialog by remember { mutableStateOf(true) }

        if (showDialog) {
            ManageAssignmentDetails(
                title2 = "Science Project",
                description = "Build a working model of a volcano.",
                timeCreated = "May 5, 2025",
                dueDate = "May 10, 2025",
                teacherName = "Mrs. Adams",
                className = "Science 7A",
                onClose = { showDialog = false }
            )
        }

 */