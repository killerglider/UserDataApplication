package com.example.application

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.application.databinding.UserDataBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddNewUser : AppCompatActivity() {


    private lateinit var binding: UserDataBinding
    private val userViewModel: UserViewModel by viewModels {
        UserItemModelFactory((application as UserDataApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val userName = binding.inpName.text.toString()
            val userEmail = binding.inpEmail.text.toString()
            val userPhone = binding.inpPhone.text.toString()

            val newUser = UserItem(userName, userEmail, userPhone)
            userViewModel.addUserItem(newUser)

            binding.inpName.setText(" ")
            binding.inpEmail.setText(" ")
            binding.inpPhone.setText(" ")
            finish()
        }


    }
}