package com.sammyg.assessmate.ui.theme.screens.management.teachers.cards


import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.Upload
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.SessionManager
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.Purple


@Composable
fun SubmittedAssignmentCard(
    assignment: Assignment,
    authViewModel: UserAuthViewModel,
    navController: NavController,
    userName: String,
    onUploadClick: () -> Unit
) {

    val context = LocalContext.current


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(
                red = 255,
                green = 255,
                blue = 255,
                alpha = 143
            ),
        ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            ) {


                Spacer(modifier = Modifier.height(8.dp))

                Text(text = assignment.className, fontSize = 16.sp, color = Color.Black)

                Text(
                    text = userName,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = assignment.assigntitle, fontSize = 16.sp, color = Color.Black)
                }
            }

            Button(
                onClick = {
                    val userName = authViewModel.currentUserData.value?.name
                    if (userName.isNullOrBlank()) {
                        Toast.makeText(context, "Unknown user", Toast.LENGTH_SHORT).show()
                        return@Button
                    }

                    SessionManager.fetchCurrentUserSchoolName { schoolName ->
                        if (schoolName.isNullOrBlank()) {
                            Toast.makeText(context, "School not found", Toast.LENGTH_SHORT).show()
                            return@fetchCurrentUserSchoolName
                        }

                        val urlRef = FirebaseDatabase.getInstance()
                            .getReference("$schoolName/Assignments/${assignment.assignId}/$userName/submittedfile")

                        urlRef.get()
                            .addOnSuccessListener { snap ->
                                val downloadUrl = snap.getValue(String::class.java)
                                if (downloadUrl.isNullOrBlank()) {
                                    Toast.makeText(context, "No file URL found", Toast.LENGTH_SHORT).show()
                                    return@addOnSuccessListener
                                }
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(downloadUrl))
                                    .apply {
                                        addCategory(Intent.CATEGORY_BROWSABLE)
                                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                                    }
                                if (intent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(intent)
                                } else {
                                    Toast.makeText(context, "No browser found", Toast.LENGTH_SHORT).show()
                                }
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                },
                modifier = Modifier
                    .padding(end = 1.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Download,
                        contentDescription = "Download Assignment",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Download \nAssignment",
                        lineHeight = 12.sp,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                }
            }

            Button(
                onClick = { onUploadClick},
                modifier = Modifier
                    .padding(end = 16.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Black
                ),
                contentPadding = PaddingValues(8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.Upload,
                        contentDescription = "Upload Results",
                        modifier = Modifier.size(40.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Upload \nResults",
                        lineHeight = 12.sp,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubmittedAssignmentCardPreview() {
    val sampleAssignment = Assignment(
        teacher = "Mr. Smith",
        className = "Grade 10 - A",
        assigntitle = "Math Homework",
        assigndescription = "Solve the equations",
        dueDate = "May 10",
        fileURL = "https://example.com/file.pdf",
        createdTime = "9:00 AM",
        assignId = "sample123"
    )

    SubmittedAssignmentCard(
        assignment = sampleAssignment,
        authViewModel = UserAuthViewModel(NavController(LocalContext.current), LocalContext.current),
        navController = NavController(LocalContext.current),
        userName = "John Doe",
        onUploadClick = {}
        )
}
