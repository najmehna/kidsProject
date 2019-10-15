package com.najmehnasiriyani.kidsapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_view_survey.*

class ViewSurveyActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    val TAG = ViewSurveyActivity::class.java.simpleName
    var children = ArrayList<Child>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_survey)

        listView.setOnItemClickListener { adapterView, view, i, l ->
          var intent = Intent(this, ChildDetailActivity::class.java)
            intent.putExtra("childID", children[i].id)
            startActivity(intent)
            //intent.putExtra("child", children[i])
        }
    }

    override fun onStart() {
        super.onStart()
        children = ArrayList()
        populateList()

    }
//    fun populateList(){
//        val docRef = db.collection("Kids")//.document()
//        docRef.addSnapshotListener { snapshot, e ->
//            if (e != null) {
//                Log.w(TAG, "Listen failed.", e)
//                return@addSnapshotListener
//            }
//
////            if (snapshot != null && snapshot.exists()) {
//            if (snapshot != null) {
//                Log.d(TAG, "Current data: ${snapshot.documents}")
//                Toast.makeText(this, "The data is here finally${snapshot.documents}", Toast.LENGTH_LONG).show()
//
//            } else {
//                Log.d(TAG, "Current data: null")
//                Toast.makeText(this, "there is no data to show", Toast.LENGTH_LONG).show()
//            }
//        }
//    }


    fun populateList(){
        db.collection("Kids")
            .get()
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "The data is here finally${task.result}", Toast.LENGTH_LONG).show()
                    Log.d(TAG, "Task result is: "  + " => " + task.result!!.count())

                    for (document in task.result!!) {

                        Log.d(TAG, "Current data is: " + document.id + " => " + document.data)
                        val item = document.data
                        val child = Child(document.id, item["name"].toString(), item["country"] as Long, item["familyWealth"] as Long,item["dob"].toString(), item["health"] as Long, item["notes"].toString())
                        children.add(child)
                    }
                    val myCustomAdapter = CustomAdapter(this, children)
                    listView.adapter = myCustomAdapter
                } else {
                    Log.d(TAG, "Error getting documents: ", task.exception)
                }
            }
    }
//
//        val docRef = db.collection("cities").document("SF")
//        docRef.addSnapshotListener { snapshot, e ->
//            if (e != null) {
//                Toast.makeText(this, "Failed to listen to data...Here is the error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
//
//                return@addSnapshotListener
//            }
//
//            if (snapshot != null && snapshot.exists()) {
//              //  Log.d(TAG, "Current data: ${snapshot.data}")
//               // children = snapshot.data.
//            } else {
//               // Log.d(TAG, "Current data: null")
//            }
//        }
//        for(i in 0..4){
//            val child = Child("Nora", "Nora@yahoo.com", (i* i).toString(),"$i / $i / $i")
//            children.add(child)
//        }
//    }

}
