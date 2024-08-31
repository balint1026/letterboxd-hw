package com.balint1026.tellhw.repositories



import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.balint1026.tellhw.models.User
import kotlinx.coroutines.tasks.await

class UserRepository {

    private val db = FirebaseFirestore.getInstance()
    private val usersCollection = db.collection("users")

    suspend fun createUser(user: User) {
        usersCollection.document(user.uid).set(user).await()
    }

    suspend fun getUser(uid: String): User? {
        val document = usersCollection.document(uid).get().await()
        return document.toObject<User>()
    }

    suspend fun updateUser(user: User) {
        usersCollection.document(user.uid).set(user).await()
    }

    suspend fun deleteUser(uid: String) {
        usersCollection.document(uid).delete().await()
    }
}