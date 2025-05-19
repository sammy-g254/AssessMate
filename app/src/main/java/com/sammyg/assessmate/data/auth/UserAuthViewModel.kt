package com.sammyg.assessmate.data.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.data.database.SessionManager
import com.sammyg.assessmate.models.auth.User
import com.sammyg.assessmate.navigation.ROUT_MAIN_LOGIN
import com.sammyg.assessmate.navigation.ROUT_MAIN_REGISTER
import com.sammyg.assessmate.navigation.ROUT_STUDENT_DASHBOARD
import com.sammyg.assessmate.navigation.ROUT_TEACHER_DASHBOARD

class UserAuthViewModel(var navController: NavController, var context: Context){
    val mAuth: FirebaseAuth



    init {
        mAuth = FirebaseAuth.getInstance()
    }

    // Holds the currently signed-in user's full data
    private val _currentUserData = mutableStateOf<User?>(null)
    val currentUserData: State<User?> = _currentUserData

    fun mainsignup(
        name: String,
        email: String,
        schoolCode: String,
        password: String,
        confpassword: String,
        studentOrTeacher: String
    ) {
        if (name.isBlank() || email.isBlank() || schoolCode.isBlank() || password.isBlank() || confpassword.isBlank()) {
            Toast.makeText(context, "Name, email, school code, and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        } else if (password != confpassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
            return
        }

        // 🔍 Verify school code
        val schoolRef = FirebaseDatabase.getInstance().getReference()
        val query = schoolRef.orderByChild("info/schoolCode").equalTo(schoolCode)

        query.get().addOnSuccessListener { snapshot ->
            if (!snapshot.exists()) {
                Toast.makeText(context, "School does not exist in our database", Toast.LENGTH_LONG).show()
                return@addOnSuccessListener
            }

            // Retrieve matched school name
            var matchedSchoolName: String? = null
            snapshot.children.forEach { matchedSchoolName = it.key }
            matchedSchoolName?.let { schoolName ->
                // Create user in Firebase Auth
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val userId = mAuth.currentUser!!.uid
                            val user = User(
                                name = name,
                                email = email,
                                schoolCode = schoolCode,
                                password = password,
                                studentorteacher = studentOrTeacher,
                                id = userId
                            )

                            _currentUserData.value = user

                            // Save user under schoolName/Role/Name/info
                            val userRef = FirebaseDatabase.getInstance()
                                .getReference("$schoolName/$studentOrTeacher/$name/info")
                            userRef.setValue(user).addOnCompleteListener { saveTask ->
                                if (saveTask.isSuccessful) {
                                    Toast.makeText(context, "Registered Successfully", Toast.LENGTH_SHORT).show()
                                    if (studentOrTeacher.equals("Student", true)) {
                                        navController.navigate(ROUT_STUDENT_DASHBOARD){
                                            popUpTo(0) { inclusive = true } // clears entire backstack
                                            launchSingleTop = true // avoids creating multiple copies of the same destination
                                        }
                                    } else {
                                        navController.navigate(ROUT_TEACHER_DASHBOARD){
                                            popUpTo(0) { inclusive = true } // clears entire backstack
                                            launchSingleTop = true // avoids creating multiple copies of the same destination
                                        }
                                    }
                                }
                            }
                        } else {
                            Toast.makeText(context, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUT_MAIN_REGISTER)
                        }
                    }
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Servers failed: ${it.message}", Toast.LENGTH_LONG).show()
        }
    }

    fun mainsignin(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        }

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (!authTask.isSuccessful) {
                    Toast.makeText(context, "Login failed: incorrect email or password", Toast.LENGTH_LONG).show()
                    return@addOnCompleteListener
                }else{

                val currentUid = mAuth.currentUser!!.uid
                val dbRef = FirebaseDatabase.getInstance().getReference()

                dbRef.get().addOnSuccessListener { snapshot ->
                    // Search through School->Student/Teacher
                    snapshot.children.forEach { school ->
                        val schoolName = school.key ?: return@forEach

                        // Check Students
                        school.child("Student").children.forEach { node ->
                            val idInDb = node.child("info/id").getValue(String::class.java)
                            if (idInDb == currentUid) {
                                val user = node.child("info").getValue(User::class.java)
                                if (user != null) {
                                    _currentUserData.value = user
                                }
                                Toast.makeText(context, "Welcome Student", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(ROUT_STUDENT_DASHBOARD){
                                    popUpTo(0) { inclusive = true } // clears entire backstack
                                    launchSingleTop = true // avoids creating multiple copies of the same destination
                                }
                                return@addOnSuccessListener
                            }
                        }

                        // Check Teachers
                        school.child("Teacher").children.forEach { node ->
                            val idInDb = node.child("info/id").getValue(String::class.java)
                            if (idInDb == currentUid) {
                                val user = node.child("info").getValue(User::class.java)
                                if (user != null) {
                                    _currentUserData.value = user
                                }
                                Toast.makeText(context, "Welcome Teacher", Toast.LENGTH_SHORT)
                                    .show()
                                navController.navigate(ROUT_TEACHER_DASHBOARD){
                                    popUpTo(0) { inclusive = true } // clears entire backstack
                                    launchSingleTop = true // avoids creating multiple copies of the same destination
                                }
                                return@addOnSuccessListener
                            }
                        }
                    }

                    Toast.makeText(context, "User role not found", Toast.LENGTH_SHORT).show()
                    }
                }.addOnFailureListener {
                    Toast.makeText(context, "Failed to get user data", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUT_MAIN_LOGIN) {
            popUpTo(0)  // clear backstack so user can’t hit “back” into the app
        }
    }





    fun isLoggedIn(): Boolean = mAuth.currentUser != null
}
