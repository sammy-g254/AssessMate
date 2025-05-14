package com.sammyg.assessmate.data.database

import android.content.Context
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

    init {
        // Check if user is logged in
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            // User not logged in, redirect to login page
            navController.navigate(ROUT_MAIN_LOGIN)
        } else {
            // 2️⃣ Once we know the school, start listening:
            SessionManager.fetchCurrentUserSchoolName { schoolName ->
                if (schoolName != null) {
                    val assignRef = FirebaseDatabase.getInstance().getReference("$schoolName/Assignments")
                    // Listen for any child added/changed/removed
                    assignRef.addValueEventListener(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            _assignments.clear()
                            snapshot.children.forEach { child ->
                                val a = child.getValue(Assignment::class.java)
                                if (a != null) _assignments.add(a)
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            Toast.makeText(context, "Failed to load assignments", Toast.LENGTH_SHORT).show()
                        }
                    })
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
                    assignId = assignId
                )

                // Save assignment in Firebase under <schoolname>/Assignments/<assignId>
                val assignmentRef = FirebaseDatabase.getInstance().getReference("$schoolName/Assignments/$assignId")
                assignmentRef.setValue(assignment).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this.context, "Assignment created successfully", Toast.LENGTH_SHORT).show()
                        navController.navigate(ROUT_TEACHER_DASHBOARD)
                    } else {
                        Toast.makeText(this.context, "Error uploading assignment", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this.context, "Failed to fetch school name", Toast.LENGTH_SHORT).show()
            }
        }
    }

/*    // Fetch all assignments for a specific school
    fun getAssignments(
        assignments: SnapshotStateList<NewAssignment>
    ): SnapshotStateList<NewAssignment> {
        // Fetch the school name and use it to get assignments
        SessionManager.fetchCurrentUserSchoolName { schoolName ->
            if (schoolName != null) {
                val assignmentsRef = FirebaseDatabase.getInstance().getReference("$schoolName/Assignments")
                assignmentsRef.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        assignments.clear()
                        for (snap in snapshot.children) {
                            val retrievedAssignment = snap.getValue(NewAssignment::class.java)
                            if (retrievedAssignment != null) {
                                assignments.add(retrievedAssignment)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(context, "Database error", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this.context, "Failed to fetch school name", Toast.LENGTH_SHORT).show()
            }
        }
        return assignments
    }

 */
}
