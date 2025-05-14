package com.sammyg.assessmate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sammyg.assessmate.data.auth.SchoolAuthViewModel
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.AssignmentViewModel
import com.sammyg.assessmate.ui.theme.screens.dashboard.SchoolDashboard
import com.sammyg.assessmate.ui.theme.screens.dashboard.StudentDashboard
import com.sammyg.assessmate.ui.theme.screens.dashboard.TeacherDashboard
import com.sammyg.assessmate.ui.theme.screens.login.MainLogin
import com.sammyg.assessmate.ui.theme.screens.login.SchoolLogin
import com.sammyg.assessmate.ui.theme.screens.management.AccessAllAssignments
import com.sammyg.assessmate.ui.theme.screens.management.school.ManageAssignment
import com.sammyg.assessmate.ui.theme.screens.management.school.ManageStudents
import com.sammyg.assessmate.ui.theme.screens.management.school.ManageTeachers
import com.sammyg.assessmate.ui.theme.screens.management.students.ManageCurrentAssignment
import com.sammyg.assessmate.ui.theme.screens.management.students.NewAssignments
import com.sammyg.assessmate.ui.theme.screens.management.teachers.CreateAssignment
import com.sammyg.assessmate.ui.theme.screens.management.teachers.ManageSubmittedAssignments
import com.sammyg.assessmate.ui.theme.screens.register.MainRegister
import com.sammyg.assessmate.ui.theme.screens.register.SchoolRegister

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_MAIN_LOGIN
) {

     // 1️⃣ Create one shared Auth VM here:
    val context = LocalContext.current
    val authViewModel = remember { UserAuthViewModel(navController, context) }
    val schoolauthViewModel = remember { SchoolAuthViewModel(navController, context) }
    val assignmentViewModel = remember { AssignmentViewModel(navController, context, authViewModel) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_MAIN_LOGIN) {
            MainLogin(navController, authViewModel)
        }
        composable(ROUT_SCHOOL_LOGIN) {
            SchoolLogin(navController, schoolauthViewModel)
        }

        composable(ROUT_MAIN_REGISTER) {
            MainRegister(navController, authViewModel)
        }

        composable(ROUT_SCHOOL_REGISTER) {
            SchoolRegister(navController, schoolauthViewModel)
        }

        composable(ROUT_SCHOOL_DASHBOARD) {
            SchoolDashboard(navController, schoolauthViewModel)
        }

        composable(ROUT_SCHOOL_MANAGE_ASSIGNMENTS) {
            ManageAssignment(navController)
        }

        composable(ROUT_SCHOOL_MANAGE_STUDENTS) {
            ManageStudents(navController)
        }

        composable(ROUT_SCHOOL_MANAGE_TEACHERS) {
            ManageTeachers(navController)
        }

        composable(ROUT_STUDENT_DASHBOARD) {
            StudentDashboard(navController, authViewModel)
        }

        composable(ROUT_STUDENT_NEW_ASSIGNMENTS) {
            NewAssignments(navController, assignmentViewModel, authViewModel)
        }

        composable(ROUT_STUDENT_MANAGE_ASSIGNMENTS) {
            ManageCurrentAssignment(navController)
        }

        composable(ROUT_ACCESS_ALL_ASSIGNMENTS) {
            AccessAllAssignments(navController)
        }

        composable(ROUT_TEACHER_DASHBOARD) {
            TeacherDashboard(navController, authViewModel)
        }

       composable(ROUT_TEACHER_CREATE_ASSIGNMENTS) {
            CreateAssignment(navController, assignmentViewModel, authViewModel)
        }
        composable(ROUT_TEACHER_MANAGE_ASSIGNMENTS) {
            ManageSubmittedAssignments(navController)
        }


    }
}


