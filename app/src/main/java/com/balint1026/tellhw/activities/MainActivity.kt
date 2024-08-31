package com.balint1026.tellhw.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.balint1026.tellhw.R
import com.balint1026.tellhw.models.User
import com.balint1026.tellhw.viewmodels.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val user = User(uid = "123", username = "john_doe", email = "john@example.com")
        userViewModel.createUser(user)

        userViewModel.getUser("123") { retrievedUser ->
            if (retrievedUser != null) {
                findViewById<TextView>(R.id.textView).text =
                    "Name: ${retrievedUser.username}\nEmail: ${retrievedUser.email}"
            } else {
                findViewById<TextView>(R.id.textView).text = "User not found"
            }
        }
    }
}
