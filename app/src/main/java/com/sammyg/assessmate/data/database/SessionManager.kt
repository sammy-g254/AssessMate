package com.sammyg.assessmate.data.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

object SessionManager {
    fun fetchCurrentUserSchoolName(callback: (String?) -> Unit) {
        val currentUid = FirebaseAuth.getInstance().currentUser?.uid ?: return callback(null)
        val rootRef = FirebaseDatabase.getInstance().reference

        rootRef.get().addOnSuccessListener { snapshot ->
            for (school in snapshot.children) {
                val schoolName = school.key ?: continue

                for (role in listOf("Student", "Teacher")) {
                    val roleNode = school.child(role)
                    for (user in roleNode.children) {
                        val userInfo = user.child("info")
                        val uidInDb = userInfo.child("id").getValue(String::class.java)
                        if (uidInDb == currentUid) {
                            callback(schoolName)
                            return@addOnSuccessListener
                        }
                    }
                }
            }
            callback(null) // Not found
        }.addOnFailureListener {
            callback(null)
        }
    }
    fun fetchCurrentUsername(callback: (String?) -> Unit) {
        val currentUid = FirebaseAuth.getInstance().currentUser?.uid ?: return callback(null)
        val rootRef = FirebaseDatabase.getInstance().reference

        rootRef.get().addOnSuccessListener { snapshot ->
            for (school in snapshot.children) {
                for (role in listOf("Student", "Teacher")) {
                    val roleNode = school.child(role)
                    for (user in roleNode.children) {
                        val userInfo = user.child("info")
                        val uidInDb = userInfo.child("id").getValue(String::class.java)
                        if (uidInDb == currentUid) {
                            val username = user.key
                            callback(username)
                            return@addOnSuccessListener
                        }
                    }
                }
            }
            callback(null) // Not found
        }.addOnFailureListener {
            callback(null)
        }
    }
}
