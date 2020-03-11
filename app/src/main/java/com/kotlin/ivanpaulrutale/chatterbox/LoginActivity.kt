package com.kotlin.ivanpaulrutale.chatterbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_button.setOnClickListener {
            loginUser()
        }

        no_account_register_textView.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(){
        val email = email_login.text.toString()
        val password = password_login.text.toString()

        if(email.isEmpty() || password.isEmpty())
            Toast.makeText(
                this,
                "Come on, dude.",
                Toast.LENGTH_LONG).show()

        else
            FirebaseAuth.getInstance().
                signInWithEmailAndPassword(email,password).
                addOnCompleteListener {
                    if (!it.isSuccessful)
                        return@addOnCompleteListener

                    else
                        Toast.makeText(
                            this,
                            "Signed In user with email:${it.result?.user?.email}",
                            Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this,
                        "FAILED:${it.message}", Toast.LENGTH_LONG).show()

                }
    }
}
