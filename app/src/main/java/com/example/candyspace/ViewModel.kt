package com.example.candyspace

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModel (private val repository: Repository): ViewModel() {
    val usersList: MutableLiveData<Response<ListOfUsers>> = MutableLiveData()

    fun getUsers(){
        viewModelScope.launch{
            val response: Response<ListOfUsers> = repository.getUsers()
            usersList.value = response
        }
    }

    fun getUsers(searchPhrase: String){
        viewModelScope.launch{
            val response: Response<ListOfUsers> = repository.getUsers(searchPhrase)
            usersList.value = response
        }
    }

    val singleUserInfo: MutableLiveData<Response<UserDetails>> = MutableLiveData()

    fun getUserInfo(userId: Int) {
        viewModelScope.launch{
            val response: Response<UserDetails> = repository.getUserInfo(userId)
            singleUserInfo.value = response
        }
    }

    val userTopTags: MutableLiveData<Response<TopTagList>> = MutableLiveData()

    fun getUserTopTags(userId: Int) {
        viewModelScope.launch{
            val response: Response<TopTagList> = repository.getUserTopTags(userId)
            userTopTags.value = response
        }
    }
}