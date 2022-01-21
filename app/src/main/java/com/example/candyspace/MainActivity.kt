package com.example.candyspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        viewModel.getUsers()

        viewModel.myResponse.observe(this, {response ->
            if (response.isSuccessful){
                Log.d("RESP", response.body().toString())
                Log.d("RESP", response.body()?.items!!.toString())
            }else{
                Log.d("RESP", "UNSUCCESSFUL:"+response.errorBody().toString())
            }
        })


    }
}