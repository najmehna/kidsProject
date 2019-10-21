package com.najmehnasiriyani.kidsapplication

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import java.util.*
import kotlinx.android.synthetic.main.activity_take_survey.*
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat

class TakeSurveyActivity : AppCompatActivity() {
    var db = FirebaseFirestore.getInstance()
    var cal = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_survey)

        //Setting up the calendar to show the selected date in the birthday field
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        //Setting the submit button actions
        submitButton.setOnClickListener {
            if(fieldsAreOk()) {
                createChild()

            }
        }

        //Poping the view on click of cancel button
        cancelButton.setOnClickListener {
            this.finish()
        }

        //Setting the birthday field to show calendar on click
        DOBText.setOnClickListener {
            DatePickerDialog(this@TakeSurveyActivity,
                dateSetListener,
                // set DatePickerDialog to point to today's date when it loads up
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        //Setting up the country spinner
        val myCountryAdapter = customSpinnerAdapter(countryAndFlag, this)
        spinner3.adapter = myCountryAdapter

        }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        DOBText.setText(sdf.format(cal.getTime()))
    }
    fun emptyFields(){
        DOBText.setText("")
        spinner3.setSelection(0)
        spinner.setSelection(0)
        spinner2.setSelection(0)
        childNameText.setText("")
        notesText.setText("")
        childNameText.requestFocus()
    }
    fun createChild(){
        val child = Child(null,childNameText.text.toString(),spinner3.selectedItemPosition.toLong(),spinner2.selectedItemPosition.toLong(), DOBText.text.toString(), spinner.selectedItemPosition.toLong(), notesText.text.toString())
        db.collection("Kids").add(child).addOnSuccessListener {
            Toast.makeText(this, "Data Uploaded successfully", Toast.LENGTH_LONG).show()
            runOnUiThread {
                emptyFields()
            }
        }
            .addOnFailureListener{
                Toast.makeText(this, "Failed to upload data...${it}", Toast.LENGTH_LONG).show()
            }
    }

    fun fieldsAreOk(): Boolean{
        if (childNameText.getText().toString() == "" || spinner3.selectedItemPosition == 0 || spinner.selectedItemPosition == 0 || spinner2.selectedItemPosition == 0 || DOBText.getText().toString() == "") {
            Toast.makeText(this, "Please fill all fields...", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

}
