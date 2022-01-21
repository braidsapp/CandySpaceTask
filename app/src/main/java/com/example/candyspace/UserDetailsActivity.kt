package com.example.candyspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var tvTemp: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        tvTemp = findViewById(R.id.textViewTemp)

        val userId: Int = intent.getIntExtra("id", -1)
        Log.d("INT", userId.toString())

        tvTemp.text = userId.toString()
    }
}