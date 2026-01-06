package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val validUsername= "Kamal"
    private val validPassword= "1234"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtUsername= findViewById<EditText>(R.id.edt_username)
        val edtPassword= findViewById<EditText>(R.id.edt_password)
        val btnLogin= findViewById<Button>(R.id.btn_login)

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()

            when {
                username.isEmpty() || password.isEmpty() -> {
                    Toast.makeText(
                        this, "Please enter username and password",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                username== validUsername && password==validPassword ->{
                    Toast.makeText(
                        this, "Login Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else->{
                    Toast.makeText(
                        this,
                        "Login Successful\nUsername: $username",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }
}
