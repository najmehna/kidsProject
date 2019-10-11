package com.najmehnasiriyani.kidsapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_sign_in.*

//import sun.jvm.hotspot.utilities.IntArray



class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        auth = FirebaseAuth.getInstance()
        signInBtn.setOnClickListener {
            if (allFieldsOk()){
                signMeIn()

            }
            // make a toast on button click event
        }
        signUpBtn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
    fun allFieldsOk(): Boolean{
        if (EmailText.getText().toString() == "" || passwordText.getText().toString() == "") {
            Toast.makeText(this, "Please fill out all the fields...", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }
    fun signMeIn(){
        auth.signInWithEmailAndPassword(EmailText.getText().toString(), passwordText.getText().toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    // Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    startActivity(Intent(this, HomeActivity::class.java))
                    saveCurrentUser(user!!.email)
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    // Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    // updateUI(null)
                }

                // ...
            }
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
