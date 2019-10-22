package com.google.norinori6791.pdefender.ui.webview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebModel : ViewModel() {

//    private val _text = MutableLiveData<String>().apply {
//        value = "https://www.google.co.jp"
//    }
//    val text: LiveData<String> = _text
    val text = MutableLiveData<String>()
}