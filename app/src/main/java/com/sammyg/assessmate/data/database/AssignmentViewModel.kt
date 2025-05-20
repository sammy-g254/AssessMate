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
    var navController: NavHostController,
    var context: Context,
    var userAuthViewModel: UserAuthViewModel
) {


    private val databaseReference = FirebaseDatabase.getInstance().getReference("Assignments")

    private val _assignments = mutableStateListOf<Assignment>()
    val assignments: SnapshotStateList<Assignment> = _assignments

    private val _createdAssignments = mutableStateListOf<Assignment>()
    val createdAssignments: SnapshotStateList<Assignment> = _createdAssignments


    // Initialize the ViewModel
    init {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            SessionManager.fetchCurrentUserSchoolName { schoolName: String? ->
                if (!schoolName.isNullOrEmpty()) {
                    SessionManager.fetchCurrentUsername { currentUsername: String? ->
                        if (!currentUsername.isNullOrEmpty()) {
                            val assignRef = FirebaseDatabase.getInstance()
                                .getReference("$schoolName/Assignments")

                            assignRef.addValueEventListener(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    Log.d("AssignmentViewModel", "Firebase onDataChange triggered")
                                    _assignments.clear()
                                    _createdAssignments.clear()

                                    if (!snapshot.exists()) {
                                        Log.d("AssignmentViewModel", "No assignments found at path")
                                    }

                                    for (child in snapshot.children) {
                                        val assignment = child.getValue(Assignment::class.java)
                                        if (assignment != null) {
                                            Log.d("AssignmentViewModel", "Loaded assignment: ${assignment.assigntitle} by ${assignment.teacher}")
                                            _assignments.add(assignment)
                                            if (assignment.teacher == currentUsername) {
                                                _createdAssignments.add(assignment)
                                            }
                                        } else {
                                            Log.d("AssignmentViewModel", "Failed to parse assignment from snapshot: ${child.key}")
                                        }
                                    }
                                    Log.d("AssignmentViewModel", "Total assignments loaded: ${_assignments.size}")
                                    Log.d("AssignmentViewModel", "Created assignments count: ${createdAssignments.size}")

                                }

                                override fun onCancelled(error: DatabaseError) {
                                    Log.e("AssignmentViewModel", "Firebase listener cancelled: ${error.message}")
                                    Toast.makeText(
                                        context,
                                        "Failed to load assignments: ${error.message}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            })
                        } else {
                            Toast.makeText(context, "Failed to get your username", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Failed to get your school name", Toast.LENGTH_SHORT).show()
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
                    .getReference("$schoolName/Assignments/$assignId")
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
        onSuccess: () -> Unit,        // ← callback instead
        onError: (Throwable) -> Unit  // ← optional error callback
        ) {
        SessionManager.fetchCurrentUserSchoolName { schoolName ->
            if (schoolName != null) {
                val assignmentRef = FirebaseDatabase.getInstance()
                    .getReference("$schoolName/Assignments/$assignId")

                val updates = mapOf<String, Any>(
                    "className" to className,
                    "assigntitle" to title,
                    "assigndescription" to description,
                    "fileURL" to fileURL,
                    "dueDate" to dueDate
                )

                assignmentRef.updateChildren(updates)
                    .addOnSuccessListener { onSuccess() }
                    .addOnFailureListener { e -> onError(e) }
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