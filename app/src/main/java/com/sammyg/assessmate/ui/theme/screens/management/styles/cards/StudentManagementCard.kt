package com.sammyg.assessmate.ui.theme.screens.management.styles.cards

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.Purple

@Composable
fun StudentManagementCard(
    assignment: Assignment,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clickable { onClick() }
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "By: ${assignment.teacher}", fontSize = 16.sp,color = Color.Black)
                    Text(text = assignment.className, fontSize = 16.sp,color = Color.Black)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun StudentManagementCardPreview() {
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

    StudentManagementCard(
        assignment = sampleAssignment,
        onClick = {}
    )
}
