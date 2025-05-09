package com.sammyg.assessmate.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sammyg.assessmate.ui.theme.screens.dashboard.SchoolDashboard
import com.sammyg.assessmate.ui.theme.screens.dashboard.StudentDashboard
import com.sammyg.assessmate.ui.theme.screens.dashboard.TeacherDashboard
import com.sammyg.assessmate.ui.theme.screens.login.MainLogin
import com.sammyg.assessmate.ui.theme.screens.login.SchoolLogin
import com.sammyg.assessmate.ui.theme.screens.register.MainRegister
import com.sammyg.assessmate.ui.theme.screens.register.SchoolCodeGen
import com.sammyg.assessmate.ui.theme.screens.register.SchoolRegister

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_MAIN_LOGIN
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_MAIN_LOGIN) {
            MainLogin(navController)
        }
        composable(ROUT_SCHOOL_LOGIN) {
            SchoolLogin(navController)
        }

        composable(ROUT_MAIN_REGISTER) {
            MainRegister(navController)
        }

        composable(ROUT_SCHOOL_CODE_GEN) {
            SchoolCodeGen(navController)
        }

        composable(ROUT_SCHOOL_REGISTER) {
            SchoolRegister(navController)
        }

        composable(ROUT_SCHOOL_DASHBOARD) {
            SchoolDashboard(navController)
        }

        composable(ROUT_STUDENT_DASHBOARD) {
            StudentDashboard(navController)
        }

        composable(ROUT_TEACHER_DASHBOARD) {
            TeacherDashboard(navController)
        }


    }
}


//navController: NavHostController
//navController = rememberNavController()

