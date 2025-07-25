package com.example.thinkzy.activities

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.thinkzy.R
import com.example.thinkzy.databinding.DialogProgressBinding
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {

    private lateinit var progressBar: Dialog

    private lateinit var bindingCustom: DialogProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingCustom = DialogProgressBinding.inflate(layoutInflater)

    }

    fun showProgressDialog(text : String){
        progressBar = Dialog(this)

        progressBar.setContentView(R.layout.dialog_progress)

        progressBar.setContentView(bindingCustom.root)

        bindingCustom.tvProgressBar.text = text

        progressBar.show()
    }

    fun hideProgressDialog(){
        progressBar.dismiss()
    }
    fun showErrorSnackBar(message : String){
        val snackBar = Snackbar.make(findViewById(android.R.id.content),
            message, Snackbar.LENGTH_LONG)
        val snackBarView = snackBar.view

        snackBarView.setBackgroundColor(ContextCompat.getColor(this,
            R.color.green))

        snackBar.show()
    }

    fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}