package com.sammyg.assessmate.data.auth

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.sammyg.assessmate.models.auth.User

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.sammyg.assessmate.navigation.ROUT_MAIN_REGISTER


class AuthViewModel(var navController: NavController, var context: Context){
    val mAuth: FirebaseAuth

    init {
        mAuth = FirebaseAuth.getInstance()
    }
fun MainRegister(name:String, email:String, schoolcode:String, password:String,confpassword:String){

    if (name.isBlank() || email.isBlank() || schoolcode.isBlank() || password.isBlank() || confpassword.isBlank()){
            Toast.makeText(context,"Please email,school code and password cannot be blank", Toast.LENGTH_LONG).show()
        }else if (password != confpassword){
            Toast.makeText(context,"Passwords do not match", Toast.LENGTH_LONG).show()
        }else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                if (it.isSuccessful){
                    val userdata= User(name,email,password,mAuth.currentUser!!.uid)
                    val regRef=FirebaseDatabase.getInstance().getReference()
                        .child("Users/"+mAuth.currentUser!!.uid)
                    regRef.setValue(userdata).addOnCompleteListener {

                        if (it.isSuccessful){
                            Toast.makeText(context,"Registered Successfully", Toast.LENGTH_LONG).show()

                        }else{
                            Toast.makeText(context,"${it.exception!!.message}", Toast.LENGTH_LONG).show()
                            navController.navigate(ROUT_MAIN_REGISTER)
                        }
                    }
                }else{
                    navController.navigate(ROUT_MAIN_REGISTER)
                }

            } }

    }

    fun login(email: String, password: String){

        if (email.isBlank() || password.isBlank()){
            Toast.makeText(context,"Please email and password cannot be blank", Toast.LENGTH_LONG).show()
        }
        else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful ){
                    Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                    navController.navigate(ROUT_HOME)
                }else{
                    Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    fun logout(){
        mAuth.signOut()
        navController.navigate(ROUT_HOME)
    }

    fun isLoggedIn(): Boolean = mAuth.currentUser != null

}