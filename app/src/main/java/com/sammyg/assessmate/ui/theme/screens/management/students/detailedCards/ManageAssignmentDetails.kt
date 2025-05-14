package com.sammyg.assessmate.ui.theme.screens.management.students.detailedCards

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.ui.theme.Purple
import android.content.Intent
import android.net.Uri

@Composable
fun ManageAssignmentDetails(
    navController: NavHostController,
    assigntitle: String,
    assigndescription: String,
    createdTime: String,
    dueDate: String,
    teacher: String,
    className: String,
    fileURL: String,
    assignId: String,
    schoolName: String,
    context: Context,
    authViewModel: UserAuthViewModel,
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
                    Text(text = assigntitle, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = assigndescription, fontSize = 16.sp)

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(text = "Created: $createdTime", color = Color.Gray, fontSize = 14.sp)
                    Text(text = "Due: $dueDate", color = Color.Red, fontSize = 14.sp)
                    Text(text = "Teacher: $teacher", color = Color.DarkGray, fontSize = 14.sp)
                    Text(text = "Class: $className", color = Color.DarkGray, fontSize = 14.sp)

                    var fileURL by remember { mutableStateOf(fileURL) }


                    OutlinedTextField(
                        value = fileURL,
                        onValueChange = { fileURL = it },
                        label = { Text("File URL") },
                        leadingIcon = { Icon(Icons.Default.Assignment, contentDescription = null) },
                        colors = OutlinedTextFieldDefaults.colors(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text(
                        text = "The URL you are going to paste above is the link to the assignment which you have uploaded to a third - party app or website. An example is the link generated for public file sharing in cloud services like Google Drive when you choose to share an uploaded file.",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(red = 103, green = 58, blue = 183, alpha = 255),
                        modifier = Modifier
                            .background(
                                color = Color.White.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .fillMaxWidth()
                            .padding(start = 20.dp, end = 20.dp),
                        textAlign = TextAlign.Center,
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            val username = authViewModel.currentUserData.value?.name ?: return@Button
                            val studentRef = FirebaseDatabase.getInstance()
                                .getReference("$schoolName/Assignments/$assignId/$username")

                            studentRef.child("submittedFileURL").setValue(fileURL)
                            studentRef.child("submittedTitle").setValue(assigntitle)

                            // Optional: remove from downloaded list to hide from new assignments
                            FirebaseDatabase.getInstance()
                                .getReference("$schoolName/NewAssignments/$username/$assignId").removeValue()

                            Toast.makeText(context, "Submitted successfully", Toast.LENGTH_SHORT).show()
                            onClose()
                        }) {
                            Text("Submit")
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fileURL)).apply {
                            addCategory(Intent.CATEGORY_BROWSABLE)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }

                            val packageManager = context.packageManager
                            if (intent.resolveActivity(packageManager) != null) {
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "No browser found to open the link", Toast.LENGTH_SHORT).show()
                            }
                        }) {
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