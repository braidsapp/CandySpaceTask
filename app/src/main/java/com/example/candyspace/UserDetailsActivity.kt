package com.example.candyspace

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import java.util.*

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

    private lateinit var tvToolbarTitle: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details)

        val repository = Repository()
        val viewModelFactory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ViewModel::class.java]
        val userId: Int = intent.getIntExtra("id", -1)
        viewModel.getUserInfo(userId)
        viewModel.getUserTopTags(userId)

        tvName = findViewById(R.id.textViewName)
        tvReputation = findViewById(R.id.textViewReputation)
        tvTopTags = findViewById(R.id.textViewTopTags)
        tvBadges = findViewById(R.id.textViewBadges)
        tvLocation = findViewById(R.id.textViewLocation)
        tvCreationDate = findViewById(R.id.textViewCreationDate)
        ivAvatar = findViewById(R.id.imageViewAvatar)

        tvToolbarTitle = findViewById(R.id.toolbar_title)

        ivBackIcon = findViewById(R.id.imageViewBackIcon)
        ivBackIcon.setOnClickListener{
            onBackPressed()
        }

        viewModel.userTopTags.observe(this, {response ->
            if (response.isSuccessful){
                Log.d("TGS", response.body()?.items!!.toString())
                val tags: ArrayList<Tag> = response.body()?.items!!
                var displayString = ""
                for (i in 0 until tags.count()){
                    displayString += if (i == tags.count()-1){
                        tags[i].tag_name
                    }else {
                        tags[i].tag_name + ", "
                    }
                }
                tvTopTags.text = displayString
            }else{
                Log.d("TGS", "UNSUCCESSFUL:"+response.errorBody().toString())
            }
        })

        viewModel.singleUserInfo.observe(this, {response ->
            if (response.isSuccessful){
                val userInfo: UserInfo = response.body()?.items!![0]

                val timeStamp: Long= userInfo.userSinceUNIX.toLong()
                val sdf = java.text.SimpleDateFormat("dd-MM-yyyy")
                val date = Date(timeStamp * 1000)
                val formattedDate: String = sdf.format(date)

                tvName.text = userInfo.name
                tvReputation.text = "Reputation: "+userInfo.reputation.toString()
                //tvTopTags.text = userInfo
                tvBadges.text = "Badges: "+userInfo.badgeCounts.gold.toString()+" Gold, "+userInfo.badgeCounts.silver.toString()+" Silver, "+userInfo.badgeCounts.bronze.toString()+" Bronze"
                tvLocation.text = "Location: "+userInfo.location
                tvCreationDate.text = "Member Since: " + formattedDate
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