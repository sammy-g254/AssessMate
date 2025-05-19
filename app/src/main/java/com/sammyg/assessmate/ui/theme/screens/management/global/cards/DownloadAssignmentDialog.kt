package com.sammyg.assessmate.ui.theme.screens.management.global.cards

import android.content.Context
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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.sammyg.assessmate.ui.theme.Purple
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement

@Composable
fun DownloadAssignmentDetails(
    assigntitle: String,
    assigndescription: String,
    createdTime: String,
    dueDate: String,
    teacher: String,
    className: String,
    fileURL: String,
    context: Context,
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


                    Spacer(modifier = Modifier.height(24.dp))


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            if (fileURL.isNotBlank()) {

                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fileURL)).apply {
                                addCategory(Intent.CATEGORY_BROWSABLE)
                                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                            }

                            val packageManager = context.packageManager
                            if (intent.resolveActivity(packageManager) != null) {
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(
                                    context,
                                    "No browser found to open the link",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            } else {
                                Toast.makeText(context, "File URL is empty", Toast.LENGTH_SHORT).show()
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



