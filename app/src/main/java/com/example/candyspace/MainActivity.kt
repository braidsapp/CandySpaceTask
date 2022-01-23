package com.example.candyspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), UserRVAdapter.OnItemClickListener {

    private lateinit var viewModel: ViewModel
    private lateinit var rvUserList: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var btnSearch: Button
    private lateinit var etSearch: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUserList = findViewById(R.id.recyclerViewUserList)
        etSearch = findViewById(R.id.editTextSearch)
        btnSearch = findViewById(R.id.buttonSearch)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]
        viewModel.getUsers()

        btnSearch.setOnClickListener {
            val searchPhrase: String = etSearch.text.toString()
            viewModel.getUsers(searchPhrase)
        }

        viewModel.usersList.observe(this, {response ->
            if (response.isSuccessful){

                userList = response.body()?.items!!

                rvUserList.adapter = UserRVAdapter(userList, this)
                rvUserList.layoutManager = LinearLayoutManager(this)
                rvUserList.setHasFixedSize(true)
                rvUserList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

            }else{
                Log.d("RESP", "UNSUCCESSFUL:"+response.message().toString())
            }
        })
    }

    override fun onItemClick(position: Int) {

        Log.d("CLK", userList[position].toString())

        val userDetailsActivity: Intent = Intent(this, UserDetailsActivity::class.java).apply{
            putExtra("id", userList[position].id)
        }
        startActivity(userDetailsActivity)

        val userId: Int = userList[position].id
        viewModel.getUserInfo(userId)
    }
}