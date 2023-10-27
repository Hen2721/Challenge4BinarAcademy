package com.example.challenge4.activity.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.challenge4.R
import com.example.challenge4.activity.home.MainActivity
import com.example.challenge4.core.di.UserViewModelFactory
import com.example.challenge4.core.domain.model.User
import com.example.challenge4.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        binding.btnAbc.setOnClickListener {
            if (validateForm()) {
                createAccount()
            }
        }
    }

    private fun validateForm(): Boolean {
        var countError = 0

        if (binding.name.text.toString().isEmpty()) {
            binding.name.error = "Nama tidak boleh kosong"
            countError++
        }

        if (binding.etEmail.text.toString().isEmpty()) {
            binding.etEmail.error = "Email tidak boleh kosong"
            countError++
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(binding.etEmail.text.toString())
                .matches()
        ) {
            binding.etEmail.error = "Email tidak valid"
            countError++
        }

        if (binding.etUsername.text.toString().isEmpty()) {
            binding.etUsername.error = "Username tidak boleh kosong"
            countError++
        } else if (binding.etUsername.text.toString().length < 6) {
            binding.etUsername.error = "Username minimal 6 karakter"
            countError++
        } else if (binding.etUsername.text.toString().contains(" ")) {
            binding.etUsername.error = "Username tidak boleh mengandung spasi"
            countError++
        }

        if (binding.etPassword.text.toString().isEmpty()) {
            binding.etPassword.error = "Password tidak boleh kosong"
            countError++
        }

        if (binding.etRetypePassword.text.toString().isEmpty()) {
            binding.etRetypePassword.error = "Konfirmasi password tidak boleh kosong"
            countError++
        } else if (binding.etPassword.text.toString() != binding.etRetypePassword.text.toString()) {
            binding.etRetypePassword.error = "Password tidak sama"
            countError++
        }

        return countError <= 0
    }

    private fun createAccount() {
        val factory = UserViewModelFactory.getInstance(this)
        registerViewModel = ViewModelProvider(this, factory)[RegisterViewModel::class.java]

        registerViewModel.getUserByEmail(binding.etEmail.text.toString()).observe(this) { user ->
            if (user != null) {
                binding.etEmail.error = "Email sudah terdaftar"
                binding.etEmail.requestFocus()
                Toast.makeText(
                    this,
                    "Email sudah terdaftar, silahkan coba email lain",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(this, "Berhasil mendaftar", Toast.LENGTH_SHORT).show()
                val name = binding.name.text.toString()
                val email = binding.etEmail.text.toString()
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()
                val phoneNumber = binding.etPhoneNumber.text.toString()

                val userCreate = User(
                    id = 0,
                    name = name,
                    username = username,
                    email = email,
                    phone = phoneNumber,
                    password = password
                )

                val sharedPreferences = getSharedPreferences("isUserLogin", MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putBoolean("isUserLogin", true)
                editor.apply()

                val accountData = getSharedPreferences("userAccount", MODE_PRIVATE)
                val accountEdit = accountData.edit()
                accountEdit.putString("email", email)
                accountEdit.apply()

                registerViewModel.insertUser(userCreate)
                startActivity(Intent(this, MainActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }
}