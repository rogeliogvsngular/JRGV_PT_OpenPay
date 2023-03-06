package com.sngular.rgarciavital.data.repositoty

import com.sngular.rgarciavital.data.TmdbAppService
import com.sngular.rgarciavital.data.model.PeliculaResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeliculasRepo @Inject constructor(
    private val Tmdbappservice: TmdbAppService
) {

    //Obtiene las peliculas mas populares
    suspend fun getPopulares(apikey: String): Response<PeliculaResult> = withContext(
        Dispatchers.IO) {
        val populares = Tmdbappservice.getPopularMovies(apikey)
        populares
    }

    //Obtiene las peliculas mas calificadas
    suspend fun getTopReview(apikey: String): Response<PeliculaResult> = withContext(
        Dispatchers.IO) {
        val topreview = Tmdbappservice.getTopReview(apikey)
        topreview
    }

    //Obtiene las mejores recomendaciones de peliculas
    suspend fun getTopRecommended(apikey: String): Response<PeliculaResult> = withContext(
        Dispatchers.IO) {
        val toprecommended = Tmdbappservice.getTopRecommended(apikey)
        toprecommended
    }

}