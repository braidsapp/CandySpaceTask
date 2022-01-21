package com.example.candyspace

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
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

        viewModel.myResponse.observe(this, {response ->
            if (response.isSuccessful){

                userList = response.body()?.items!!

                rvUserList.adapter = UserRVAdapter(userList, this)
                rvUserList.layoutManager = LinearLayoutManager(this)
                rvUserList.setHasFixedSize(true)
                rvUserList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

            }else{
                Log.d("RESP", "UNSUCCESSFUL:"+response.errorBody().toString())
            }
        })
    }

    override fun onItemClick(position: Int) {

        Log.d("CLK", userList.get(position).toString())
        Toast.makeText(this, userList.get(position).id.toString(), Toast.LENGTH_SHORT)

        val userDetailsActivity: Intent = Intent(this, UserDetailsActivity::class.java).apply{
            putExtra("id", userList.get(position).id)
        }
        startActivity(userDetailsActivity)

        //supportFragmentManager.beginTransaction().replace(R.id.frameLayoutDetail, UserDetailsFragment()).commit()

        val userId: Int = userList.get(position).id
        viewModel.getUserInfo(userId)
    }
}