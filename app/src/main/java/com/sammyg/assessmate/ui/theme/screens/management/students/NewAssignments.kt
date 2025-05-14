package com.sammyg.assessmate.ui.theme.screens.management.students

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.R
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.AssignmentViewModel
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.screens.management.students.detailedCards.NewAssignmentDetails
import com.sammyg.assessmate.ui.theme.screens.management.styles.TopManagementNavBar
import com.sammyg.assessmate.ui.theme.screens.management.styles.cards.StudentManagementCard


@Composable
fun NewAssignments(navController: NavHostController, assignmentViewModel: AssignmentViewModel, authViewModel: UserAuthViewModel) {

    var selected by remember { mutableStateOf<Assignment?>(null) }

    Scaffold(
        topBar = {
            TopManagementNavBar(title = "New Assignments")
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

            // 2️⃣ Loop through the live assignments list
            assignmentViewModel.assignments.forEach { a ->
                StudentManagementCard(
                    assignment = a,          // pass the whole Assignment
                    onClick    = { selected = a }
                )
            }
        }

        // 4️⃣ If an assignment is selected, pop up details
        selected?.let { a ->
            NewAssignmentDetails(
                navController   = navController,
                authViewModel     = authViewModel,    // ← pass it down
                assignId          = a.assignId,        // ← the unique ID
                assigntitle     = a.assigntitle,
                assigndescription = a.assigndescription,
                createdTime     = a.createdTime,
                dueDate         = a.dueDate,
                teacher         = a.teacher,
                className       = a.className,
                fileURL         = a.fileURL
            ) {
                selected = null  // onClose
            }


        }

    }
}





@Preview(showBackground = true)
@Composable
fun NewAssignmentPreview(){
    NewAssignments(
        navController = rememberNavController(),
        assignmentViewModel = AssignmentViewModel(navController = rememberNavController(), context = LocalContext.current, authViewModel = TODO()),
        authViewModel = UserAuthViewModel( navController = rememberNavController(), context = LocalContext.current)
    )
}