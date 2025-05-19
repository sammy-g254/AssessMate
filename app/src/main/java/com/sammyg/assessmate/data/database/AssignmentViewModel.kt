package com.sammyg.assessmate.data.database

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.data.auth.UserAuthViewModel
import com.sammyg.assessmate.models.database.Assignment
import com.sammyg.assessmate.navigation.ROUT_MAIN_LOGIN
import com.sammyg.assessmate.navigation.ROUT_TEACHER_DASHBOARD
import androidx.compose.runtime.mutableStateListOf
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AssignmentViewModel(
    var context: Context,
    var userAuthViewModel: UserAuthViewModel
) {

    val isUserLoggedIn = FirebaseAuth.getInstance().currentUser != null

    private val databaseReference = FirebaseDatabase.getInstance().getReference("Assignments")

    private val _assignments = mutableStateListOf<Assignment>()
    val assignments: SnapshotStateList<Assignment> = _assignments

    val createdAssignments = mutableStateListOf<Assignment>()

    // Initialize the ViewModel
    init {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            // Redirect if not logged in
        } else {
            // First get school name
            SessionManager.fetchCurrentUserSchoolName { schoolName ->
                if (schoolName != null) {
                    // Then get the current user's username
                    SessionManager.fetchCurrentUsername { currentUsername ->
                        if (currentUsername != null) {
                            val assignRef = FirebaseDatabase.getInstance()
                                .getReference("$schoolName/Assignments")

                            assignRef.addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    _assignments.clear()
                                    createdAssignments.clear()

                                    snapshot.children.forEach { child ->
                                        // Now explicitly dive into the "info" child under each <assignId>
                                        val infoSnap = child.child("info")
                                        val a = infoSnap.getValue(Assignment::class.java)
                                        if (a != null) {
                                            _assignments.add(a)

                                            // Filter by current teacher
                                            if (a.teacher == currentUsername) {
                                                createdAssignments.add(a)
                                            }
                                        }
                                    }
                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Toast.makeText(
                                        context,
                                        "Failed to load assignments",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                        } else {
                            Toast.makeText(
                                context,
                                "Failed to get your username",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        context,
                        "Failed to get your school name",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }


    fun createAssignment(
        teacher: String,
        className: String,
        assigntitle: String,
        assigndescription: String,
        dueDate: String,
        fileURL: String,
        createdTime: String
    ) {
        // Generate unique ID for the assignment
        val assignId = System.currentTimeMillis().toString()

        // Get current user and school name
        val currentUser = FirebaseAuth.getInstance().currentUser
        val userId = currentUser?.uid

        // Fetch school name from session manager
        SessionManager.fetchCurrentUserSchoolName { schoolName ->
            if (schoolName != null) {
                // Create the assignment object
                val assignment = Assignment(
                    teacher = teacher,
                    className = className,
                    assigntitle = assigntitle,
                    assigndescription = assigndescription,
                    dueDate = dueDate,
                    fileURL = fileURL,
                    createdTime = createdTime,
                    assignId = assignId,
                )

                // Save assignment in Firebase under <schoolname>/Assignments/<assignId>
                val assignmentRef = FirebaseDatabase.getInstance()
                    .getReference("$schoolName/Assignments/$assignId/info")
                assignmentRef.setValue(assignment).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this.context,
                            "Assignment created successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(ROUT_TEACHER_DASHBOARD)
                    } else {
                        Toast.makeText(
                            this.context,
                            "Error uploading assignment",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(this.context, "Failed to fetch school name", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun updateAssignment(
        assignId: String,
        className: String,
        title: String,
        description: String,
        fileURL: String,
        dueDate: String,
        context: Context,
        navController: NavController
    ) {
        SessionManager.fetchCurrentUserSchoolName { schoolName ->
            if (schoolName != null) {
                val assignmentRef = FirebaseDatabase.getInstance()
                    .getReference("$schoolName/Assignments/$assignId/info")

                val updates = mapOf<String, Any>(
                    "className" to className,
                    "assigntitle" to title,
                    "assigndescription" to description,
                    "fileURL" to fileURL,
                    "dueDate" to dueDate
                )

                assignmentRef.updateChildren(updates)
                    .addOnSuccessListener {
                        Toast.makeText(
                            context,
                            "Assignment updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        navController.navigate(ROUT_TEACHER_DASHBOARD)
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(context, "Failed to update assignment", Toast.LENGTH_SHORT)
                            .show()
                        Log.e("AssignmentViewModel", "Failed to update assignment: ${e.message}")
                    }
            } else {
                Toast.makeText(context, "Failed to fetch school name", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun deleteAssignment(assignId: String) {
        SessionManager.fetchCurrentUserSchoolName { schoolName ->
            if (schoolName != null) {
                val ref = FirebaseDatabase.getInstance()
                    .getReference("$schoolName/Assignments/$assignId")

                ref.removeValue()
                    .addOnSuccessListener {
                        Log.d("AssignmentViewModel", "Assignment deleted")
                    }
                    .addOnFailureListener { e ->
                        Log.e("AssignmentViewModel", "Failed to delete: ${e.message}")
                    }
            }
        }
    }
}


