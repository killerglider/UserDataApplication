package com.example.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.UUID

class UserViewModel(private val repository: UserItemRepository) : ViewModel() {
    var userItems: LiveData<List<UserItem>> = repository.allUserItems.asLiveData()

    public fun addUserItem(newUser: UserItem) = viewModelScope.launch {
        repository.insertUserItem(newUser)
    }


    fun updateUserItem(userItem: UserItem) = viewModelScope.launch {
        repository.updateUserItem(userItem)
    }


}

@Suppress("UNCHECKED_CAST")
class UserItemModelFactory(private val repository: UserItemRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java))
            return UserViewModel(repository) as T

            throw IllegalArgumentException("Unknown Class for View Model")


    }
}