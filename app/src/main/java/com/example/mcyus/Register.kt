package com.example.mcyus

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mcyus.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
//
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


        firebaseAuth = FirebaseAuth.getInstance()

        binding.Register.setOnClickListener {
            val email : String = binding.editTextEmail.text.toString().trim()
            val password : String = binding.editTextPassword.text.toString().trim()
            val  confirmPassword : String = binding.editTexKonfirmasitPassword.text.toString().trim()

            if (email.isEmpty()){
                binding.editTextEmail.error = "Input Email"
                binding.editTextEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.editTextEmail.error ="Invalid Password"
                binding.editTextEmail.requestFocus()
                return@setOnClickListener
            }

            if(password.isEmpty() || password.length < 6){
                binding.editTextPassword.error = "password be more tthan 6 characters"
                binding.editTextPassword.requestFocus()
                return@setOnClickListener            }

            if(password != confirmPassword){
                binding.editTextPassword.error = "password mus be match"
                binding.editTextPassword.requestFocus()
                return@setOnClickListener
            }
            registerUser(email, password)
        }

    }

    private fun registerUser(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                Intent(this, Home::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
            else{
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
}