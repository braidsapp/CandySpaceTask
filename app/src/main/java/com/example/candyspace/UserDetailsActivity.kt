package com.example.candyspace

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    private lateinit var tvName: TextView
    private lateinit var tvReputation: TextView
    private lateinit var tvTopTags: TextView
    private lateinit var tvBadges: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvCreationDate: TextView
    private lateinit var ivAvatar: ImageView
    private lateinit var ivBackIcon: ImageView

    /*private lateinit var toolbar: Toolbar*/
    private lateinit var tvToolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]
        val userId: Int = intent.getIntExtra("id", -1)
        viewModel.getUserInfo(userId)

        tvName = findViewById(R.id.textViewName)
        tvReputation = findViewById(R.id.textViewReputation)
        tvTopTags = findViewById(R.id.textViewTopTags)
        tvBadges = findViewById(R.id.textViewBadges)
        tvLocation = findViewById(R.id.textViewLocation)
        tvCreationDate = findViewById(R.id.textViewCreationDate)
        ivAvatar = findViewById(R.id.imageViewAvatar)

        tvToolbarTitle = findViewById(R.id.toolbar_title)
        /*toolbar = findViewById(R.id.toolbar)

        toolbar.setNavigationOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        })*/
        ivBackIcon = findViewById(R.id.imageViewBackIcon)
        ivBackIcon.setOnClickListener(View.OnClickListener {
            onBackPressed()
        })

        viewModel.singleUserInfo.observe(this, {response ->
            if (response.isSuccessful){
                var data: String = response.body().toString()   //UserDetails(items=[UserInfo(avatar=https://www.g
                var userInfo: UserInfo = response.body()?.items!!.get(0)
                Log.e("DEETS", data.toString())

                val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val date = java.util.Date(1532358895 * 1000)
                sdf.format(date)

                tvName.text = userInfo.name
                tvReputation.text = "Reputation: "+userInfo.reputation.toString()
                //tvTopTags.text = userInfo
                tvBadges.text = "Badges: "+userInfo.badgeCounts.gold.toString()+" Gold, "+userInfo.badgeCounts.silver.toString()+" Silver, "+userInfo.badgeCounts.bronze.toString()+" Bronze"
                tvLocation.text = "Location: "+userInfo.location
                tvCreationDate.text = "Member since: "+userInfo.userSinceUNIX.toString()
                Picasso.get().load(userInfo.avatar).into(ivAvatar)

                tvToolbarTitle.text = userInfo.name

            }else{
                Log.d("RESP", "UNSUCCESSFUL:"+response.errorBody().toString())
            }
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        finishAffinity()
    }
}