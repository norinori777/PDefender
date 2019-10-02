package com.google.norinori6791.pdefender.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "https://www.google.co.jp"
//    }
//    val text: LiveData<String> = _text
    val text = MutableLiveData<String>("https://www.google.co.jp")
}