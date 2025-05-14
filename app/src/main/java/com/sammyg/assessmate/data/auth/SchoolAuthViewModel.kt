package com.sammyg.assessmate.data.auth

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.NavController
import com.sammyg.assessmate.models.auth.School
import androidx.compose.runtime.State
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.navigation.ROUT_MAIN_REGISTER
import com.sammyg.assessmate.navigation.ROUT_SCHOOL_DASHBOARD
import com.sammyg.assessmate.navigation.ROUT_SCHOOL_REGISTER


class SchoolAuthViewModel(var navController: NavController, var context: Context){
    val mAuth: FirebaseAuth

    init {
        mAuth = FirebaseAuth.getInstance()
    }

    private val _currentSchoolData = mutableStateOf<School?>(null)
    val currentSchoolData: State<School?> = _currentSchoolData

    fun schoolsignup(schoolname: String, schoolemail: String, schoolCode: String, schoolpassword: String, schoolconfpassword: String) {
        if (schoolname.isBlank() || schoolemail.isBlank() || schoolCode.isBlank() || schoolpassword.isBlank()) {
            Toast.makeText(context, "Please fill all required fields", Toast.LENGTH_LONG).show()
        } else if (schoolpassword != schoolconfpassword) {
            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(schoolemail, schoolpassword).addOnCompleteListener {
                if (it.isSuccessful) {
                    val schoolId = mAuth.currentUser!!.uid

                    // Store only safe school data (NOT password)
                    val schoolInfo = mapOf(
                        "schoolname" to schoolname,
                        "schoolemail" to schoolemail,
                        "schoolCode" to schoolCode,
                        "schoolpassword" to schoolpassword,
                        "uid" to schoolId
                    )

                    val regRef = FirebaseDatabase.getInstance()
                        .getReference("$schoolname/info")

                    regRef.setValue(schoolInfo).addOnCompleteListener {
                        if (it.isSuccessful) {
                            _currentSchoolData.value = School(
                                schoolname    = schoolname,
                                schoolemail   = schoolemail,
                                schoolCode    = schoolCode,
                                schoolpassword= schoolpassword,
                                id            = mAuth.currentUser!!.uid
                            )
                            Toast.makeText(context, "Registered Successfully: Welcome, school manager", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUT_SCHOOL_DASHBOARD)
                        } else {
                            Toast.makeText(context, "${it.exception?.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Registration failed: ${it.exception?.message}", Toast.LENGTH_LONG).show()
                    navController.navigate(ROUT_SCHOOL_REGISTER)
                }
            }
        }
    }


    fun schoolsignin(email: String, password: String){

        if (email.isBlank() || password.isBlank()){
            Toast.makeText(context,"Email and password cannot be blank", Toast.LENGTH_LONG).show()
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful ){
                    val rootRef = FirebaseDatabase.getInstance().getReference()
                    rootRef.get().addOnSuccessListener { snap ->
                        snap.children.forEach { schoolNode ->
                            val info = schoolNode.child("info")
                            if (info.child("uid")
                                    .getValue(String::class.java) == mAuth.currentUser!!.uid
                            ) {
                                val school = info.getValue(School::class.java)
                                if (school != null) {
                                    // ─── NEW: set your state ─────────────────────
                                    _currentSchoolData.value = school
                                    // ───────────────────────────────────────────────
                                }
                                return@addOnSuccessListener
                            }
                        }
                    }
                    Toast.makeText(this.context, "Login successful: Welcome school manager", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_SCHOOL_DASHBOARD)
                }else{
                    Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun isLoggedIn(): Boolean {
        return mAuth.currentUser != null
    }

}