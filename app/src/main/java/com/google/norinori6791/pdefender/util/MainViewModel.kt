package com.google.norinori6791.pdefender.util

import androidx.lifecycle.MutableLiveData

class MainViewModel {
    val moveAdd = MutableLiveData<Boolean>()

    fun onFabClicked(){
        moveAdd.postValue(true)
    }
}