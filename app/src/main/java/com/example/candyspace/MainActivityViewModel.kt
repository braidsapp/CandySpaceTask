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
            /*Log.d("RESPi", response.body().toString())
            Log.d("RESPi", response.body()?.items!!.toString())

            val receivedData: ArrayList<User> = response.body()?.items!!
            for (i in 0 until receivedData.count()){
                var id: Int = receivedData[i].id
                var name: String = receivedData[i].name
                var reputation: Int = receivedData[i].reputation

                var collective: JSONObject = JSONObject(receivedData[i].tags[0].toString())
                var tags: String = collective.optString("tags").toString()
                Log.e("RESPI", tags)
                //var tags
                *//*badges
                location
                vreation
                avatar*//*

                Log.e("respi", receivedData[i].tags[0].toString())
            }*/



        }
    }
}