package com.sngular.rgarciavital.viewmodel.perfil

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sngular.rgarciavital.data.model.*
import com.sngular.rgarciavital.util.Resource
import com.sngular.rgarciavital.util.hasInternetConnection
import com.sngular.rgarciavital.data.repositoty.PerfilRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor(
    private val perfilRepository: PerfilRepo,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val dataProfile: MutableLiveData<Resource<Author?>> = MutableLiveData()
    val reviewsPopular: MutableLiveData<Resource<ReviewResult>> = MutableLiveData()
    var reviewsListResponse: ReviewResult? = null
    var reviewsPage = 1
    var igmagesListesponse: ImageResult? = null


    fun obtenerReviews(apikey: String) = viewModelScope.launch {
        safeReviews(apikey, reviewsPage)
    }

    private suspend fun safeReviews(apikey: String, page: Int) {
        reviewsPopular.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                //Mas populares
                val response = perfilRepository.getBestPerson(apikey)
                val imageMoviePath = perfilRepository.getImageMovie(apikey)


                reviewsPopular.postValue(handleOrderResponse(response, imageMoviePath))
            } else
                reviewsPopular.postValue(Resource.Error("No Internet Connection"))
        } catch (ex: Exception) {
            Log.i("Error Perfil", ex.toString())
            when (ex) {
                is IOException -> reviewsPopular.postValue(Resource.Error("Network Failure"))
                else -> reviewsPopular.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private fun handleOrderResponse(
        response: Response<ReviewResult>,
        images: Response<ImageResult>
    ): Resource<ReviewResult> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                reviewsPage++
                var oldReviews: ArrayList<Review>?

                reviewsListResponse = resultResponse
                oldReviews = resultResponse!!.reviews as ArrayList<Review>?

                var countRev = 0
                if (oldReviews != null) {
                    if (images.isSuccessful) {
                        images.body()?.let { imageResponse ->
                            igmagesListesponse = imageResponse
                            val imageMoviesList =
                                imageResponse!!.backdrops as ArrayList<ImageMovie>?

                            for (item in oldReviews) {
                                oldReviews?.find { it.id == item.id }?.image =
                                    imageMoviesList!!.get(countRev).file_path
                                countRev++
                            }
                        }
                        dataProfile.postValue(Resource.Success(oldReviews.get(0).author_details))
                    }
                }


                return Resource.Success(reviewsListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}