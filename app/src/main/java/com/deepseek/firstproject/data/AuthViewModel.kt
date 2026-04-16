package com.deepseek.firstproject.data

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavHostController
import com.deepseek.firstproject.models.User
import com.deepseek.firstproject.navigation.ROUTE_DASHBOARD
import com.deepseek.firstproject.navigation.ROUTE_LOGIN
import com.deepseek.firstproject.navigation.ROUTE_REGISTER
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class AuthViewModel(var navController: NavHostController, var context: Context) {
    var mAuth = FirebaseAuth.getInstance()

    //register function
    fun signup(fullname: String, email: String, password: String, confirmpass: String) {
        //validation
        if (email.isBlank() || password.isBlank() || confirmpass.isBlank()) {
            Toast.makeText(context, "Email and password cannot be blank", Toast.LENGTH_LONG).show()
            return
        } else if (password != confirmpass) {
            Toast.makeText(context, "password do not match", Toast.LENGTH_LONG).show()
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val uid = mAuth.currentUser!!.uid
                        // User model: fullname, email, password, userId, role
                        val userdata = User(fullname, email, password, uid, "user")
                        //realtime db
                        val regRef = FirebaseDatabase.getInstance().getReference()
                            .child("Users/" + uid)
                        regRef.setValue(userdata).addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "user registered successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_LOGIN)
                            } else {
                                Toast.makeText(
                                    context,
                                    "${task.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                                navController.navigate(ROUTE_REGISTER)
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "${it.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                        navController.navigate(ROUTE_REGISTER)
                    }
                }
        }
    }

    //login function
    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            Toast.makeText(context, "Email and password required", Toast.LENGTH_LONG).show()
            return
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "successfully login in", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_DASHBOARD)
            } else {
                Toast.makeText(context, "error logging in: ${it.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //logout function
    fun logout() {
        mAuth.signOut()
        navController.navigate(ROUTE_LOGIN) {
            popUpTo(0)
        }
    }

    //get current user
    fun getCurrentUserName(onResult: (String) -> Unit) {
        val userId = mAuth.currentUser?.uid

        if (userId != null) {
            val ref = FirebaseDatabase.getInstance()
                .getReference("Users/$userId")

            ref.get().addOnSuccessListener { snapshot ->
                val name = snapshot.child("fullname").value.toString()
                onResult(if (name != "null") name else "User")
            }.addOnFailureListener {
                onResult("User")
            }
        } else {
            onResult("Guest")
        }
    }
}
