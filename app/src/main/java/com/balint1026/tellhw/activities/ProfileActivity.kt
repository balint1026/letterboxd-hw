package com.balint1026.tellhw.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.balint1026.tellhw.R
import com.balint1026.tellhw.repositories.FirestoreRepository
import com.google.firebase.auth.FirebaseAuth

class ProfileActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var firestoreRepository: FirestoreRepository

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = FirebaseAuth.getInstance()
        firestoreRepository = FirestoreRepository()

        val user = auth.currentUser
        val textViewWelcome: TextView = findViewById(R.id.textViewWelcome)
        val editTextUsername: EditText = findViewById(R.id.editTextUsername)
        val editTextEmail: EditText = findViewById(R.id.editTextEmail)
        val buttonSave: Button = findViewById(R.id.buttonSave)
        val buttonSignOut: Button = findViewById(R.id.buttonSignOut)

        if (user != null) {
            textViewWelcome.text = "Welcome, ${user.displayName ?: user.email ?: "User"}"
            editTextEmail.setText(user.email)
            editTextUsername.setText(user.displayName)
        }

        buttonSave.setOnClickListener {
            val email = editTextEmail.text.toString()
            val displayName = editTextUsername.text.toString()
            if (user != null) {
                updateUserInfo(user.uid, email, displayName)
            }
        }

        buttonSignOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun updateUserInfo(userId: String, email: String, displayName: String) {
        firestoreRepository.updateUserInfo(userId, email, displayName) { success ->
            if (success) {
                Toast.makeText(this, "Profile updated", Toast.LENGTH_SHORT).show()
            } else {
                Log.w("ProfileActivity", "Error updating profile")
                Toast.makeText(this, "Profile update failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
