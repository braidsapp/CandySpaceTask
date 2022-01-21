package com.example.candyspace

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel (private val repository: Repository): ViewModel() {
    val myResponse: MutableLiveData<Response<ListOfUsers>> = MutableLiveData()

    fun getUsers(){
        viewModelScope.launch{
            val response: Response<ListOfUsers> = repository.getUsers()
            myResponse.value = response
        }
    }

    fun getUsers(searchPhrase: String){
        viewModelScope.launch{
            val response: Response<ListOfUsers> = repository.getUsers(searchPhrase)
            myResponse.value = response
        }
    }

    val singleUserInfo: MutableLiveData<Response<UserDetails>> = MutableLiveData()

    fun getUserInfo(userId: Int) {
        viewModelScope.launch{
            val response: Response<UserDetails> = repository.getUserInfo(userId)
            singleUserInfo.value = response
        }

    }
}