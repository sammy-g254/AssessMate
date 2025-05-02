package com.sammyg.assessmate.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sammyg.assessmate.ui.theme.screens.login.MainLogin

/*@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_SPLASH
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        /*composable(ROUT_SPLASH) {
            SplashScreen(navController)
        }*/
        composable(ROUT_MAIN_LOGIN) {
            MainLogin(navController)
        }
        /*composable(ROUT_SCHOOL_LOGIN) {
            SchoolLogin(navController)
        }
        composable(ROUT_MAIN_REGISTER) {
            MainRegister(navController)
        }
        composable(ROUT_SCHOOL_REGISTER) {
            SchoolRegister(navController)
        }
        composable(ROUT_STUDENT_DASHBOARD) {
            StudentDashboard(navController)
        }
        composable(ROUT_TEACHER_DASHBOARD) {
            TeacherDashboard(navController = navController)
        }
        composable(ROUT_SCHOOL_DASHBOARD) {
            SchoolDashboard(navController = navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController = navController)
        }
        /*composable(
            ROUT_EDIT_PROPERTY,
            arguments = listOf(navArgument("propertyId") { type = NavType.StringType })
        ) {
            val propertyId = it.arguments?.getString("propertyId") ?: ""
            EditPropertyScreen(navController, propertyId)
        }*/*/

    }
}

*/

