package com.sammyg.assessmate.ui.theme.screens.management.teachers.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.navigation.ROUT_TEACHER_UPDATE_ASSIGNMENTS
import com.sammyg.assessmate.ui.theme.Purple

@Composable
fun TeacherManagementCard(
    navController: NavController,
    assignment: Assignment,
    onReadClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .border(3.dp, Purple, shape = RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(
            red = 255,
            green = 255,
            blue = 255,
            alpha = 143
        ),),

        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center, // Center the column in the card
            ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // First row: time and deadline
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = assignment.createdTime, fontSize = 16.sp,color = Color.Gray)
                    Text(text = "Due: ${assignment.dueDate}", fontSize = 16.sp,color = Color.Red)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Title
                Text(
                    text = assignment.assigntitle,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple
                )

                Spacer(modifier = Modifier.height(4.dp))

                // Name and Class
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp), // taller button row
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { onReadClick() },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Black)
                    ) {
                        Icon(Icons.Default.Visibility, contentDescription = "Read", tint = Color.Black, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Read", color = Color.Black, fontSize = 16.sp)
                    }

                    TextButton(
                        onClick = {
                            // 1) Save the Assignment into the SavedStateHandle of the current entry
                            navController.currentBackStackEntry
                                ?.savedStateHandle
                                ?.set("assignmentToEdit", assignment)

                            // 2) Navigate to the Update screen
                            navController.navigate(ROUT_TEACHER_UPDATE_ASSIGNMENTS)
                        },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Black)
                    ) {
                        Icon(Icons.Default.Edit, contentDescription = "Update", tint = Color.Black, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Update", color = Color.Black, fontSize = 16.sp)
                    }

                    TextButton(
                        onClick = { onDeleteClick() },
                        modifier = Modifier
                            .weight(1f),
                        colors = ButtonDefaults.textButtonColors(contentColor = Color.Black)
                    ) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = Color.Black, modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Delete", color = Color.Black, fontSize = 16.sp)
                    }
                }

            }
        }
    }
}

