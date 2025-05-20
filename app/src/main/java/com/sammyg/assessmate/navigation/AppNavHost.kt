package com.sammyg.assessmate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.sammyg.assessmate.data.auth.SchoolAuthViewModel
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.data.database.AssignmentViewModel
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.ui.theme.screens.about.About
import com.sammyg.assessmate.ui.theme.screens.account.ManageSchoolAccount
import com.sammyg.assessmate.ui.theme.screens.account.ManageUserAccount
import com.sammyg.assessmate.ui.theme.screens.dashboard.SchoolDashboard
import com.sammyg.assessmate.ui.theme.screens.dashboard.StudentDashboard
import com.sammyg.assessmate.ui.theme.screens.dashboard.TeacherDashboard
import com.sammyg.assessmate.ui.theme.screens.login.MainLogin
import com.sammyg.assessmate.ui.theme.screens.login.SchoolLogin
import com.sammyg.assessmate.ui.theme.screens.management.global.AccessAllAssignments
import com.sammyg.assessmate.ui.theme.screens.management.students.AssignmentResults
import com.sammyg.assessmate.ui.theme.screens.management.students.CurrentAssignments
import com.sammyg.assessmate.ui.theme.screens.management.teachers.CreateAssignment
import com.sammyg.assessmate.ui.theme.screens.management.teachers.ManageCreatedAssignments
import com.sammyg.assessmate.ui.theme.screens.management.teachers.ManageSubmittedAssignments
import com.sammyg.assessmate.ui.theme.screens.management.teachers.cards.UpdateAssignment
import com.sammyg.assessmate.ui.theme.screens.register.MainRegister
import com.sammyg.assessmate.ui.theme.screens.register.SchoolRegister
import com.sammyg.assessmate.ui.theme.screens.splash.SplashScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {
    val context = LocalContext.current

    // Initialize your shared ViewModels only once and remember them
    val authViewModel = remember { UserAuthViewModel(navController, context) }
    val schoolauthViewModel = remember { SchoolAuthViewModel(navController, context) }
    val assignmentViewModel = remember { AssignmentViewModel(navController, context, authViewModel) }

    val currentUser = FirebaseAuth.getInstance().currentUser

    // Navigate to login if not logged in already on launch
    LaunchedEffect(currentUser) {
        if (currentUser == null) {
            navController.navigate(ROUT_MAIN_LOGIN) {
                popUpTo(0) { inclusive = true } // Clear navigation backstack
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_SPLASH) {
            SplashScreen(navController, authViewModel)
        }
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

        composable(ROUT_STUDENT_DASHBOARD) {
            StudentDashboard(navController, authViewModel)
        }

        composable(ROUT_STUDENT_CURRENT_ASSIGNMENTS) {
            CurrentAssignments(navController, assignmentViewModel, authViewModel)
        }

        composable(ROUT_STUDENT_ASSIGNMENT_RESULTS) {
            AssignmentResults(navController, assignmentViewModel, authViewModel)
        }

        composable(ROUT_ACCESS_ALL_ASSIGNMENTS) {
            AccessAllAssignments(navController, assignmentViewModel, authViewModel)
        }

        composable(ROUT_TEACHER_DASHBOARD) {
            TeacherDashboard(navController, authViewModel)
        }

        composable(ROUT_TEACHER_CREATE_ASSIGNMENTS) {
            CreateAssignment(navController, assignmentViewModel, authViewModel)
        }

        composable(ROUT_TEACHER_UPDATE_ASSIGNMENTS) {
            val assignmentToEdit = navController
                .previousBackStackEntry
                ?.savedStateHandle
                ?.get<Assignment>("assignmentToEdit")

            // Pass it into the composable
            UpdateAssignment(
                navController         = navController,
                assignmentViewModel   = assignmentViewModel,
                userAuthViewModel     = authViewModel,
                assignmentToEdit      = assignmentToEdit  // <— HERE
            )
        }

        composable(ROUT_TEACHER_MANAGE_SUBMITTED_ASSIGNMENTS) {
            ManageSubmittedAssignments(navController, assignmentViewModel, authViewModel)
        }

        composable(ROUT_TEACHER_MANAGE_CREATED_ASSIGNMENTS) {
            ManageCreatedAssignments(navController, assignmentViewModel, authViewModel)
        }

        composable(ROUT_ABOUT) {
            About(navController)
        }

        composable(ROUT_MANAGE_SCHOOL_ACCOUNT) {
            ManageSchoolAccount(navController, authViewModel, schoolauthViewModel)
        }

        composable(ROUT_MANAGE_USER_ACCOUNT) {
            ManageUserAccount(navController, authViewModel, schoolauthViewModel)
        }

    }
}


