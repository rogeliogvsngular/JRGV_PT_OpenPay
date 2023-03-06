package com.sngular.rgarciavital.data.local.firebase


import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Firebase  @Inject constructor(private val DB: FirebaseFirestore) {

    suspend fun getSourceLocations(): QuerySnapshot =
        withContext(Dispatchers.IO) {
            DB.collection("Source").get().await()
        }
}