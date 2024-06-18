package com.example.application

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.application.databinding.ActivityMainBinding
import com.example.application.databinding.FragmentNewUserSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewUserSheet(var userItem: UserItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewUserSheetBinding
    private lateinit var userViewModel: UserViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        if(userItem != null){
            binding.userTitle.text = "Edit User"
            val editable = Editable.Factory.getInstance()
            binding.inpName.text = editable.newEditable(userItem!!.name)
            binding.inpEmail.text = editable.newEditable(userItem!!.email)
            binding.inpPhone.text = editable.newEditable(userItem!!.phone)
        }
        else{
            binding.userTitle.text = "New User"
        }
        userViewModel = ViewModelProvider(activity).get(UserViewModel::class.java)
        binding.btnSave.setOnClickListener {
            saveAction()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentNewUserSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction() {
        val userName = binding.inpName.text.toString()
        val userEmail = binding.inpEmail.text.toString()
        val userPhone = binding.inpPhone.text.toString()
        if(userItem == null) {
            val newUser = UserItem(userName, userEmail, userPhone)
            userViewModel.addUserItem(newUser)
        }
        else{
            userItem!!.name = userName
            userItem!!.email = userEmail
            userItem!!.phone = userPhone
            userViewModel.updateUserItem(userItem!!)
        }
        binding.inpName.setText(" ")
        binding.inpEmail.setText(" ")
        binding.inpPhone.setText(" ")
        dismiss()
    }
}