package com.sngular.rgarciavital.viewmodel.peliculas

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sngular.rgarciavital.data.model.PeliculaResult
import com.sngular.rgarciavital.data.repositoty.PeliculasRepo
import com.sngular.rgarciavital.util.Resource
import com.sngular.rgarciavital.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class PeliculasViewModel @Inject constructor(
    private val peliRepository: PeliculasRepo,
    @ApplicationContext private val context: Context
) : ViewModel() {

    val pelisPopular: MutableLiveData<Resource<PeliculaResult>> = MutableLiveData()
    val pelisTopReview: MutableLiveData<Resource<PeliculaResult>> = MutableLiveData()
    val pelisTopRecommended: MutableLiveData<Resource<PeliculaResult>> = MutableLiveData()

    var pelisListResponse: PeliculaResult? = null
    var pelisPage = 1

    fun obtenerPopular(apikey: String) = viewModelScope.launch {
        safePelisPopular(apikey, pelisPage)
    }

    private suspend fun safePelisPopular(apikey: String, page: Int) {
        pelisPopular.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(context)) {
                //Mas populares
                val responsePop = peliRepository.getPopulares(apikey)
                // Las más calificadas
                val responseTop = peliRepository.getTopReview(apikey)
                //Las mejores recomendaciones.
                val responseRec = peliRepository.getTopRecommended(apikey)


                pelisPopular.postValue(handleOrderResponse(responsePop))
                pelisTopReview.postValue(handleOrderResponse(responseTop))
                pelisTopRecommended.postValue(handleOrderResponse(responseRec))

            } else {
                pelisPopular.postValue(Resource.Error("No Internet Connection"))
                pelisTopReview.postValue(Resource.Error("No Internet Connection"))
                pelisTopRecommended.postValue(Resource.Error("No Internet Connection"))
            }

        } catch (ex: Exception) {
            when (ex) {
                is IOException -> pelisPopular.postValue(Resource.Error("Network Failure"))
                else -> pelisPopular.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleOrderResponse(response: Response<PeliculaResult>): Resource<PeliculaResult> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->

                pelisListResponse = resultResponse
                return Resource.Success(pelisListResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}