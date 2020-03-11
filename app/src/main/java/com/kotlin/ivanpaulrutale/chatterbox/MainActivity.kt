package com.kotlin.ivanpaulrutale.chatterbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        register_button.setOnClickListener {
            registerUser()

        }

        already_account_textview.setOnClickListener{
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(){
        val email = email_registration.text.toString()
        val password = password_registration.text.toString()

        if(email.isEmpty() || password.isEmpty())
            Toast.makeText(
                this,
                "Come on, dude.",
                Toast.LENGTH_LONG).show()

        else
            FirebaseAuth.getInstance().
            createUserWithEmailAndPassword(email,password).
            addOnCompleteListener {
                if (!it.isSuccessful)
                    return@addOnCompleteListener

                else
                    Toast.makeText(
                    this,
                    "Created user with email:${it.result?.user?.email}",
                    Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener {
                Toast.makeText(this,
                    "FAILED:${it.message}",Toast.LENGTH_LONG).show()

            }
    }
}
