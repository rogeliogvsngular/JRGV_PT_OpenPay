package com.sngular.rgarciavital.data.repositoty

import com.sngular.rgarciavital.data.local.firebase.Firebase
import com.sngular.rgarciavital.data.model.UserData
import com.sngular.rgarciavital.util.ResourceFirebase
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

class FireBaseRepository @Inject constructor(val firebase: Firebase){

    suspend fun getSourceLocations(): Flow<ResourceFirebase<List<UserData>>> = flow {
        emit(ResourceFirebase.loading(data = null))
        println("it.data.toString()")
        emit(ResourceFirebase.success(firebase.getSourceLocations().documents.mapNotNull {
            it.toObject(UserData::class.java)
        }))
    }.catch {
        emit(ResourceFirebase.error(data = null, msg = "Something went wrong!"))
    }


}