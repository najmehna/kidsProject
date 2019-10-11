package com.najmehnasiriyani.kidsapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        signUpButton.setOnClickListener {
            if (fieldsAreOk()){
                createUser()
                startActivity(Intent(this, HomeActivity::class.java))

            }
        }

    }
    fun allFieldsOk():Boolean{
        return true
    }
    fun createUser(){
        auth.createUserWithEmailAndPassword(EmailText.text.toString(), passwordText.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    //Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(baseContext, "Authentication successful.",
                        Toast.LENGTH_SHORT).show()
                    saveUserDetails(user!!)
                    saveCurrentUser(user!!.email)
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                   // Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                // ...
            }
    }
    fun createUser(user:FirebaseUser){
        val profile = Profile(NameText.text.toString(),EmailText.text.toString(),phoneNumberText.text.toString(), DOBText.text.toString())
        db.collection("Users").document(user.uid).set(profile)
    }
    fun fieldsAreOk(): Boolean{
        if (NameText.getText().toString() == "" || EmailText.getText().toString() == "" || passwordText.getText().toString() == "" || phoneNumberText.getText().toString() == "" || DOBText.getText().toString() == "") {
            Toast.makeText(this, "Please fill all fields...", Toast.LENGTH_LONG).show()
            return false
        }
        if (passwordText.getText().toString() != passwordConfirmText.getText().toString()) {
            Toast.makeText(this, "Passwords don't match...", Toast.LENGTH_LONG).show()
            return false}
        return true
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
       // updateUI(currentUser)
    }
    fun saveUserDetails(user: FirebaseUser){

    }
    fun saveCurrentUser(user: String?){
        if (user != null) {
            val sharedPref = applicationContext?.getSharedPreferences(
                getString(R.string.SharedPrefFileName), Context.MODE_PRIVATE
            ) ?: return
            with(sharedPref.edit()) {
                putString(getString(R.string.currentUser), user)
                commit()
            }
        }
    }

}
