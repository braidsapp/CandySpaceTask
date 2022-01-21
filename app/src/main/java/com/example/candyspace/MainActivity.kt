package com.example.candyspace

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var rvUserList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainActivityViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
        viewModel.getUsers()

        viewModel.myResponse.observe(this, {response ->
            if (response.isSuccessful){
                val receivedData: ArrayList<User> = response.body()?.items!!
                Log.d("RESP", response.body().toString())
                Log.d("RESP", response.body()?.items!!.toString())
                Log.d("RESPa", receivedData.toString())
                Log.d("RESPa", receivedData[0].toString())

                var rvInfoList: ArrayList<RecyclerViewUserInfo> = ArrayList<RecyclerViewUserInfo>()

                for (i in 0 until receivedData.count()){
                    var id: Int = receivedData[0].id
                    var name: String = receivedData[0].name

                    rvInfoList.add(RecyclerViewUserInfo(id, name))
                }

                rvUserList = findViewById(R.id.recyclerViewUserList)

                rvUserList.adapter = UserRVAdapter(rvInfoList)
                rvUserList.layoutManager = LinearLayoutManager(this)
                rvUserList.setHasFixedSize(true)
                rvUserList.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))


                /*var id: Int = receivedData[0].id
                var name: String = receivedData[0].name
                var reputation: Int = receivedData[0].reputation
                var badges: BadgeCounts = receivedData[0].badges
                var name: String = receivedData[0].name
                var location: String = receivedData[0].location
                var creationDateUNIX: Int = receivedData[0].creationDateUnix
                var avatar: String = receivedData[0].avatar
                */
                //var tags: Any = receivedData[0].tags[0].opt
                //var id: Int = receivedData[0].id
                //var id: Int = receivedData[0].id

                //Log.d("RESPa", tags.toString())


            }else{
                Log.d("RESP", "UNSUCCESSFUL:"+response.errorBody().toString())
            }
        })


    }
}