package com.sngular.rgarciavital.viewmodel.mapa

import androidx.lifecycle.ViewModel
import com.sngular.rgarciavital.data.repositoty.FireBaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel  @Inject constructor(private val fireBaseRepo : FireBaseRepository) :ViewModel() {
}
