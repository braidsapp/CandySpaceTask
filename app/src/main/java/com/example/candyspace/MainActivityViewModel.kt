package com.example.candyspace

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

class MainActivityViewModel (private val repository: Repository): ViewModel() {
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
}