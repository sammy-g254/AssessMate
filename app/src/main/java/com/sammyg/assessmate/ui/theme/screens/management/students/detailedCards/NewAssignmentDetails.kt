package com.sammyg.assessmate.ui.theme.screens.management.students.detailedCards

import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.SessionManager
import com.sammyg.assessmate.navigation.ROUT_STUDENT_MANAGE_ASSIGNMENTS
import com.sammyg.assessmate.ui.theme.Purple

@Composable
fun NewAssignmentDetails(
    navController: NavHostController,
    assigntitle: String,
    assigndescription: String,
    createdTime: String,
    dueDate: String,
    teacher: String,
    className: String,
    fileURL: String, // ✅ New parameter
    onClose: () -> Unit
) {
    val context = LocalContext.current // ✅ Needed for launching intent

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

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "NEW ASSIGNMENT",
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

                    Spacer(modifier = Modifier.height(24.dp))

                    // Buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text("File URL: $fileURL", fontSize = 12.sp, color = Color.Gray)
                        Spacer(modifier = Modifier.height(12.dp))

                        Button(onClick = {
                            // ✅ Launch web browser to download the file
                            try {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fileURL))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "No browser found to open the link.", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Text("Download Assignment")
                        }
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun NewAssignmentDetailsPreview(){
    NewAssignmentDetails(
        assigntitle = "Science Project",
        assigndescription = "Build a working model of a volcano.",
        createdTime = "May 5, 2025",
        dueDate = "May 10, 2025",
        teacher = "Mrs. Adams",
        className = "Science 7A",
        onClose = {  },
        navController = rememberNavController(),
        fileURL = "https://www.google.com",
        )

}