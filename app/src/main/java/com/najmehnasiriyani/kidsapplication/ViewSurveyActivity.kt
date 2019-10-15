package com.najmehnasiriyani.kidsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_view_survey.*

class ViewSurveyActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()

    var children = ArrayList<Child>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        populateList()
        val myCustomAdapter = CustomAdapter(this, children)
        listView.adapter = myCustomAdapter
    }
    fun populateList(){

        val docRef = db.collection("cities").document("SF")
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Toast.makeText(this, "Failed to listen to data...Here is the error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()

                return@addSnapshotListener
            }

            if (snapshot != null && snapshot.exists()) {
              //  Log.d(TAG, "Current data: ${snapshot.data}")
               // children = snapshot.data.
            } else {
               // Log.d(TAG, "Current data: null")
            }
        }
        for(i in 0..4){
            val child = Child("Nora", "Nora@yahoo.com", (i* i).toString(),"$i / $i / $i")
            children.add(child)
        }
    }

}
