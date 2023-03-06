package com.sngular.rgarciavital.data.repositoty

import com.sngular.rgarciavital.data.TmdbAppService
import com.sngular.rgarciavital.data.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PerfilRepo @Inject constructor(
    private val Tmdbappservice: TmdbAppService
) {

    suspend fun getBestPerson(apikey: String): Response<ReviewResult> = withContext(
        Dispatchers.IO
    ) {
        val toprecommended = Tmdbappservice.getTopPerson(apikey)
        toprecommended
    }

    suspend fun getImageMovie(apikey: String): Response<ImageResult> = withContext(
        Dispatchers.IO
    ) {
        val toprecommended = Tmdbappservice.getImageMovie(apikey)
        toprecommended
    }


}