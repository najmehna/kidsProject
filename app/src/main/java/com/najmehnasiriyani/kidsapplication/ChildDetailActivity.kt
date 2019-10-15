package com.najmehnasiriyani.kidsapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_child_detail.*
import java.text.SimpleDateFormat
import java.util.*

class ChildDetailActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    val TAG = ChildDetailActivity::class.java.simpleName
    var cal = Calendar.getInstance()

    var childID = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child_detail)

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }
        val myCountryAdapter = customSpinnerAdapter(countryAndFlag, this)
        spinner3.adapter = myCountryAdapter
        childID = intent.getStringExtra("childID")

        populateChildDetail()

        cancelButton.setOnClickListener {
            finish()
        }
        submitButton.setOnClickListener {
            updateChildDetails()
        }
        DOBText.setOnClickListener {
            DatePickerDialog(this@ChildDetailActivity,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    fun updateChildDetails(){
        val child = Child(null,childNameText.text.toString(),spinner3.selectedItemPosition.toLong(),spinner2.selectedItemPosition.toLong(), DOBText.text.toString(), spinner.selectedItemPosition.toLong(), notesText.text.toString())
        db.collection("Kids").document(childID).delete().addOnSuccessListener {

            Toast.makeText(this, "Old data deleted successfully", Toast.LENGTH_LONG).show()

        }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to delete old data...${it}", Toast.LENGTH_LONG).show()
            }
        db.collection("Kids").add(child).addOnSuccessListener {

            Toast.makeText(this, "New data inserted successfully", Toast.LENGTH_LONG).show()

        }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to insert new data...${it}", Toast.LENGTH_LONG).show()
            }
    }
    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        DOBText.setText(sdf.format(cal.getTime()))
    }
    fun populateChildDetail(){
        val docRef = db.collection("Kids").document(childID)
        docRef.get().addOnCompleteListener{ task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: " + task.result!!.data)
                    val item = document.data!!
                    //val myName = item["name"].toString()
                    childNameText.setText(item["name"].toString())
                    DOBText.setText(item["dob"].toString())
                    spinner.setSelection((item["familyWealth"] as Long).toInt())
                    spinner2.setSelection((item["health"] as Long).toInt())
                    spinner3.setSelection((item["country"] as Long).toInt())
                    notesText.setText(item["notes"].toString())
                } else {
                    Log.d(TAG, "No such document")
                }
            } else {
                Log.d(TAG, "get failed with ", task.exception)
            }
        }

// custom object
//        val docRef = db.collection("notes").document("note-id")
//        docRef.get().addOnSuccessListener(OnSuccessListener<DocumentSnapshot> { documentSnapshot ->
//            val note = documentSnapshot.toObject(Note::class.java)
//        })
    }
    fun updateUI(){

    }
}
