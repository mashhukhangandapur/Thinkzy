package com.example.thinkzy.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.thinkzy.R
import com.example.thinkzy.activities.AuthActivity
import com.example.thinkzy.activities.BaseActivity
import com.example.thinkzy.activities.MainActivity
import com.example.thinkzy.databinding.FragmentSigninBinding
import com.example.thinkzy.firebase.FireStoreClass
import com.example.thinkzy.models.User
import com.google.firebase.auth.FirebaseAuth

class SigninFragment : Fragment(R.layout.fragment_signin) {

    private lateinit var binding: FragmentSigninBinding
    private lateinit var baseActivity: BaseActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSigninBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity = requireActivity() as BaseActivity

        binding.btnSignin.setOnClickListener {
            signInUser()
        }

        binding.googleSignIn.setOnClickListener {
            (activity as AuthActivity).signInWithGoogle()
        }
    }

    fun signInSuccess(user: User?){
        baseActivity.hideProgressDialog()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun signInUser(){
        val email : String = binding.editTextEmailSignIn.text.toString().trim{ it <= ' '}
        val password : String = binding.editTextPasswordSignIn.text.toString().trim{ it <= ' '}

        if (validateForm(email, password)){
            baseActivity.showProgressDialog(resources.getString(R.string.please_wait))

            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email,password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        FireStoreClass().signInUser(this)
                    }
                   else {
                        baseActivity.hideProgressDialog()
                        baseActivity.showToast("Authentication failed: ${task.exception?.message}")
                    }
                }
        }
    }


    private fun validateForm(email : String, password : String) : Boolean{
        return when {
            TextUtils.isEmpty(email) ->{
                baseActivity.showErrorSnackBar("Enter your ")
                false
            }

            TextUtils.isEmpty(password) ->{
                baseActivity.showErrorSnackBar("Enter your password")
                false
            }
            else -> true
        }
    }
}