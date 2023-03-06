package com.sngular.rgarciavital.data

import com.sngular.rgarciavital.data.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbAppService {

    companion object {
        const val ENDPOINT = "https://api.themoviedb.org/3/"
    }



    @GET("movie/83542/reviews")
    suspend fun getTopPerson(@Query("api_key") apiKey: String?): Response<ReviewResult>

    @GET("movie/83542/images")
    suspend fun getImageMovie(@Query("api_key") apiKey: String?): Response<ImageResult>

   @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String?): Response<PeliculaResult>

    @GET("discover/movie/?certification_country=MX&language=es-MX&sort_by=vote_count.desc")
    suspend fun getTopReview(@Query("api_key") apiKey: String?): Response<PeliculaResult>

    @GET("discover/movie/?certification_country=MX&language=es-MXq&sort_by=vote_average.desc")
    suspend fun getTopRecommended(@Query("api_key") apiKey: String?): Response<PeliculaResult>



}