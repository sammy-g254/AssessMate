package com.sammyg.assessmate.ui.theme.screens.management.teachers




import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sammyg.assessmate.R
import com.sammyg.assessmate.ui.theme.Purple
import com.sammyg.assessmate.ui.theme.transWhite


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAssignment(onClose: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Create Assignments", fontWeight = FontWeight.Bold)
                },
                actions = {
                    IconButton(onClick = { onClose() }) {
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
        Column(
            modifier = Modifier
                .paint(
                    painter = painterResource(R.drawable.img2),
                    contentScale = ContentScale.Crop
                )
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Scrollable content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                var teacher by remember { mutableStateOf("") }
                var className by remember { mutableStateOf("") }
                var title by remember { mutableStateOf("") }
                var description by remember { mutableStateOf("") }
                var dueDate by remember { mutableStateOf("") }

                val backgroundColor = Color.White.copy(alpha = 0.5f)

                OutlinedTextField(
                    value = teacher,
                    onValueChange = { teacher = it },
                    label = { Text("Teacher's Name") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = backgroundColor,
                        focusedContainerColor = backgroundColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = className,
                    onValueChange = { className = it },
                    label = { Text("Class Name") },
                    leadingIcon = { Icon(Icons.Default.School, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = backgroundColor,
                        focusedContainerColor = backgroundColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Title") },
                    leadingIcon = { Icon(Icons.Default.Title, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = backgroundColor,
                        focusedContainerColor = backgroundColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    leadingIcon = { Icon(Icons.Default.Description, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = backgroundColor,
                        focusedContainerColor = backgroundColor
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    maxLines = 5
                )

                OutlinedTextField(
                    value = dueDate,
                    onValueChange = { dueDate = it },
                    label = { Text("Due Date") },
                    leadingIcon = { Icon(Icons.Default.Event, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = backgroundColor,
                        focusedContainerColor = backgroundColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { /* Handle Upload */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = transWhite,
                            contentColor = Color.Gray
                        )
                    ) {
                        Text(
                            text = "Upload file(s)",
                            fontSize = 18.sp
                        )
                    }
                    }


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { /* Handle Upload */ },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Purple,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Create",
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun CreateAssignmentPreview(){
    CreateAssignment()
}