package com.najmehnasiriyani.kidsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_view_survey.*

class ViewSurveyActivity : AppCompatActivity() {
    var profiles = ArrayList<Profile>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        populateList()
        val myCustomAdapter = CustomAdapter(this, profiles)
        listView.adapter = myCustomAdapter
    }
    fun populateList(){
        for(i in 0..4){
            val profile = Profile("Nora", "Nora@yahoo.com", (i* i).toString(),"$i / $i / $i")
            profiles.add(profile)
        }
    }

}
