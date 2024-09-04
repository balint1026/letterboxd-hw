package com.balint1026.tellhw.repositories

import com.balint1026.tellhw.models.Review
import com.google.firebase.firestore.FirebaseFirestore


class FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()


    fun addReview(userId: String, movieId: Int, rating: Double, reviewText: String, onComplete: (Boolean) -> Unit) {
        val review = hashMapOf(
            "userId" to userId,
            "movieId" to movieId,
            "rating" to rating,
            "reviewText" to reviewText,
            "timestamp" to System.currentTimeMillis()
        )

        db.collection("reviews")
            .add(review)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }



    fun getReviewsForMovie(movieId: Int, onReviewsLoaded: (List<Review>) -> Unit) {
        db.collection("reviews")
            .whereEqualTo("movieId", movieId)
            .get()
            .addOnSuccessListener { result ->
                val reviews = result.map { document ->
                    Review(
                        document.getString("userId") ?: "",
                        document.getLong("movieId")?.toInt() ?: 0,
                        document.getDouble("rating") ?: 0.0,
                        document.getString("reviewText") ?: "",
                        document.getLong("timestamp") ?: 0
                    )
                }
                onReviewsLoaded(reviews)
            }
            .addOnFailureListener {
                onReviewsLoaded(emptyList())
            }
    }


    fun updateUserInfo(userId: String, email: String, displayName: String, onComplete: (Boolean) -> Unit) {
        val userMap = hashMapOf(
            "displayName" to displayName,
            "email" to email
        )

        db.collection("users").document(userId)
            .set(userMap)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }
}
