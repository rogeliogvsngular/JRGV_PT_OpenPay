package com.sngular.rgarciavital.viewmodel.multimedia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class MultimediaViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Upload Fragment"
    }
    val text: LiveData<String> = _text
}