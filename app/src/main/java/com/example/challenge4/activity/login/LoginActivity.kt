package com.example.challenge4.activity.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.R
import com.example.challenge4.activity.home.HomeViewModel
import com.example.challenge4.activity.home.MainActivity
import com.example.challenge4.activity.register.RegisterActivity
import com.example.challenge4.core.di.UserViewModelFactory
import com.example.challenge4.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
        val isUserLogin = sharedPreferences.getBoolean("isUserLogin", false)
        if (isUserLogin) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }

        onClick()
    }

    private fun onClick() {
        binding.buttonLogin.setOnClickListener {
            if (validationInput()) {
                validationAccount(
                    binding.EmailL.text.toString(),
                    binding.Password.text.toString()
                )
            }
        }
        binding.Register.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validationAccount(email: String, password: String) {
        val factory = UserViewModelFactory.getInstance(this)
        loginViewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        loginViewModel.getUserByEmailAndPassword(email, password).observe(this) { user ->
            if (user != null) {
                val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isUserLogin", true)
                editor.apply()

                val accountData = getSharedPreferences("userAccount", MODE_PRIVATE)
                val accountEdit = accountData.edit()
                accountEdit.putString("email", email)
                accountEdit.apply()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finishAffinity()
            } else {
                Toast.makeText(this, "Email atau password salah", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validationInput(): Boolean {
        var countError = 0
        if (binding.EmailL.text.toString().isEmpty()) {
            binding.EmailL.error = "Email tidak boleh kosong"
            countError++
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.EmailL.text.toString())
                .matches()
        ) {
            binding.EmailL.error = "Email tidak valid"
            countError++
        }

        if (binding.Password.text.toString().isEmpty()) {
            binding.Password.error = "Password tidak boleh kosong"
            countError++
        }
        return countError <= 0
    }
}