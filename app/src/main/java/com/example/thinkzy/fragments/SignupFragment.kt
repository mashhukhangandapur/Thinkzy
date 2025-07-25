package com.example.thinkzy.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.thinkzy.R
import com.example.thinkzy.activities.AuthActivity
import com.example.thinkzy.activities.BaseActivity
import com.example.thinkzy.activities.MainActivity
import com.example.thinkzy.databinding.FragmentSignupBinding
import com.example.thinkzy.firebase.FireStoreClass
import com.example.thinkzy.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupFragment : Fragment(R.layout.fragment_signup) {

        private lateinit var baseActivity: BaseActivity
        private lateinit var binding : FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity = requireActivity() as BaseActivity

        binding.btnSignup.setOnClickListener {
            registerUser()
        }

        binding.googleSignIn.setOnClickListener {
            (activity as AuthActivity).signInWithGoogle()
        }
    }

    fun userRegisteredSuccess(){
        baseActivity.showToast("Registered Successfully")
        baseActivity.hideProgressDialog()
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun registerUser(){
        val name : String = binding.editTextName.text.toString().trim { it <= ' '}
        val email : String = binding.editTextEmail.text.toString().trim{ it <= ' '}
        val password : String = binding.editTextPassword.text.toString().trim{ it <= ' '}

        if(validateForm(name,email,password)){
            baseActivity.showProgressDialog(resources.getString(R.string.please_wait))
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                    task ->
                    if(task.isSuccessful){
                        val firebaseUser : FirebaseUser = task.result!!.user!!
                        val registeredEmail = firebaseUser.email!!
                        val user = User(firebaseUser.uid,name,registeredEmail)
                        FireStoreClass().registerUser(this, user)
                    }
                    else {
                        baseActivity.hideProgressDialog()
                        baseActivity.showToast(task.exception?.message ?: "Something went wrong")
                    }
                }
        }
    }

        private fun validateForm(name : String, email : String, password : String) : Boolean {
            return when {
                TextUtils.isEmpty(name) -> {
                    baseActivity.showErrorSnackBar("Enter your name")
                    false
                }
                 TextUtils.isEmpty(email) -> {
                     baseActivity.showErrorSnackBar("Enter your email")
                     false
                 }

                TextUtils.isEmpty(password) -> {
                    baseActivity.showErrorSnackBar("Enter your password")
                    false
                }
                else ->{
                    true
                }
            }
         }
    }