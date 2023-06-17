package com.example.firebasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firebasedemo.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {
    //lateinit var binding: ActivityRegisterBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //binding = ActivityRegisterBinding.inflate(layoutInflater)
        //val view = binding.root
        val Submitbtn = findViewById<Button>(R.id.Submitbtn)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        auth = FirebaseAuth.getInstance()
        
        Submitbtn.setOnClickListener {
            val txt_email = etEmail.text.toString()
            val txt_password = etPassword.text.toString()
            if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password)) {
                Toast.makeText(this,"Empty Credentials",Toast.LENGTH_SHORT).show()
            }
            else if(txt_password.length < 6) {
                Toast.makeText(this,"Password too short",Toast.LENGTH_SHORT).show()
            }
            else {
                registerUser(txt_email,txt_password)
            }
        }

    }

    private fun registerUser(txtEmail: String, txtPassword: String) {
        auth.createUserWithEmailAndPassword(txtEmail,txtPassword).addOnCompleteListener(this) {task->
            if(task.isSuccessful) {
                Toast.makeText(this,"Registration Successful",Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this,"Registration Failed",Toast.LENGTH_LONG).show()
            }
        }
    }

}