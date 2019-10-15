package com.najmehnasiriyani.kidsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        takeSurveyButton.setOnClickListener {
            startActivity(Intent(this, TakeSurveyActivity::class.java))
        }
        viewSurveyButton.setOnClickListener {
            startActivity(Intent(this, ViewSurveyActivity::class.java))
        }
    }
}
