package com.najmehnasiriyani.kidsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        takeSurveyButton.setOnClickListener {
            startActivity(Intent(this, TakeSurveyActivity::class.java))
        }
        viewSurveyButton.setOnClickListener {
            startActivity(Intent(this, ViewSurveyActivity::class.java))
        }
        weatherButton.setOnClickListener {
            startActivity(Intent(this, WeatherActivity::class.java))

        }
        donationButton.setOnClickListener {
            startActivity(Intent(this, DonationActivity::class.java))
        }
        signOutButton.setOnClickListener {
            signMeOut()
        }
    }
    fun signMeOut(){
        auth.signOut()
        startActivity(Intent(this, SignInActivity::class.java))
    }
}
