package com.sammyg.assessmate.ui.theme.screens.management.teachers




import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.ui.theme.Purple
import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.ui.platform.LocalContext
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.AssignmentViewModel
import com.sammyg.assessmate.navigation.ROUT_TEACHER_DASHBOARD
import java.util.Calendar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAssignment(
    navController: NavController,
    assignmentViewModel: AssignmentViewModel,
    userAuthViewModel: UserAuthViewModel, // Pass UserAuthViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Create Assignments", fontWeight = FontWeight.Bold)
                },
                actions = {
                    IconButton(onClick = {navController.popBackStack()}) {
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
                // Removed teacher text field, now fetching the teacher name from the current user
                val teacher = userAuthViewModel.currentUserData.value?.name

                var className by remember { mutableStateOf("") }
                var assigntitle by remember { mutableStateOf("") }
                var assigndescription by remember { mutableStateOf("") }
                var fileURL by remember { mutableStateOf("") }
                var dueDate by remember { mutableStateOf("") }
                var createdTime by remember { mutableStateOf("") }

                val backgroundColor = Color.White.copy(alpha = 0.5f)

                // Class Name
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

                // Assignment Title
                OutlinedTextField(
                    value = assigntitle,
                    onValueChange = { assigntitle = it },
                    label = { Text("Title") },
                    leadingIcon = { Icon(Icons.Default.Title, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = backgroundColor,
                        focusedContainerColor = backgroundColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                // Assignment Description
                OutlinedTextField(
                    value = assigndescription,
                    onValueChange = { assigndescription = it },
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

                // Due Date
                DueDateField(
                    dueDate = dueDate,
                    onDueDateChange = { dueDate = it },
                    backgroundColor = backgroundColor
                )

                // File URL
                OutlinedTextField(
                    value = fileURL,
                    onValueChange = { fileURL = it },
                    label = { Text("File URL") },
                    leadingIcon = { Icon(Icons.Default.Assignment, contentDescription = null) },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = backgroundColor,
                        focusedContainerColor = backgroundColor
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "The URL you are going to paste above is the link to the assignment which you have uploaded to a third - party app or website. An example is the link generated for public file sharing in cloud services like Google Drive when you choose to share an uploaded file.  NOTE: The URL must start with https://",
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

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
                    createdTime = sdf.format(Date())

                    Button(
                        onClick = {
                            // Validate input fields
                            if (className.isBlank() || assigntitle.isBlank() || assigndescription.isBlank() || dueDate.isBlank() || fileURL.isBlank()) {
                                Toast.makeText(
                                    navController.context,
                                    "Please fill all fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if (teacher.isNullOrBlank()) {
                                // Teacher name is missing
                                Toast.makeText(
                                    navController.context,
                                    "Teacher not found",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                // Proceed with assignment creation
                                assignmentViewModel.createAssignment(
                                    teacher!!, // Teacher name
                                    className,
                                    assigntitle,
                                    assigndescription,
                                    dueDate,
                                    fileURL,
                                    createdTime, // Pass it here

                                )

                                navController.navigate(ROUT_TEACHER_DASHBOARD)
                            }
                        },
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DueDateField(
    dueDate: String,
    onDueDateChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.White.copy(alpha = 0.5f)
) {
    // 1. Get a Calendar instance for the dialog’s default
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    // 2. Build a DatePickerDialog
    val picker = remember {
        DatePickerDialog(context, { _, y, m, d ->
            // Format as DD/MM/YYYY
            val formatted = String.format("%02d/%02d/%04d", d, m + 1, y)
            onDueDateChange(formatted)
        }, year, month, day)
    }

    // 3. Render a read‑only OutlinedTextField that shows the dialog
    OutlinedTextField(
        value = dueDate,
        onValueChange = { /* no-op */ },
        label = { Text("Due Date") },
        leadingIcon = { Icon(Icons.Default.Event, contentDescription = "Select date") },
        trailingIcon = {
            IconButton(onClick = { picker.show() }) {
                Icon(Icons.Default.CalendarMonth, contentDescription = "Select date")
            }
        },
        readOnly = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = backgroundColor,
            focusedContainerColor = backgroundColor
        ),
        modifier = modifier
            .fillMaxWidth()
            .clickable { picker.show() }
    )
}



