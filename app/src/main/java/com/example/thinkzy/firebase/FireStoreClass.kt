package com.example.thinkzy.firebase

import android.util.Log
import com.example.thinkzy.fragments.SigninFragment
import com.example.thinkzy.fragments.SignupFragment
import com.example.thinkzy.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FireStoreClass {

    private val fireStore = FirebaseFirestore.getInstance()


    fun registerUser(fragment: SignupFragment, userInfo : User){
        fireStore.collection("users")
            .document(getCurrentUserID())
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
                fragment.userRegisteredSuccess()
            }
            .addOnFailureListener { e ->
                Log.e("FireStoreClass", "Registration failed", e)
                fragment.requireActivity().run {
                    if (this is com.example.thinkzy.activities.BaseActivity) {
                        hideProgressDialog()
                    }
                }
            }
    }

    fun signInUser(fragment: SigninFragment){
        fireStore.collection("users")
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document ->
                val loggedInUser = document.toObject(User::class.java)
                fragment.signInSuccess(loggedInUser)
            }
            .addOnFailureListener { e ->
                Log.e("FireStoreClass", "Error fetching user", e)
                fragment.requireActivity().run {
                    if (this is com.example.thinkzy.activities.BaseActivity) {
                        hideProgressDialog()
                    }
                }
            }
    }


    fun getCurrentUserID(): String {
        val currentUser = FirebaseAuth.getInstance().currentUser
        return currentUser?.uid ?: ""
    }
}