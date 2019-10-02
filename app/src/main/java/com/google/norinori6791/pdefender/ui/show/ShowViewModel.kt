package com.google.norinori6791.pdefender.ui.show

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.pdefender.model.entity.AuthInfo
import java.io.Serializable

class ShowViewModel : ViewModel() {
    var item = ObservableField<AuthInfo>()
    val onCopy = MutableLiveData<OnClickData>()
    val onMove = MutableLiveData<String>("")

    fun copyToClipBord(text: String, notify: Boolean){
        val data = OnClickData(text, notify)
        onCopy.postValue(data)
    }
    fun moveSite(url: String){
        onMove.postValue(url)
    }
}

data class OnClickData (
    val text: String,
    val notify: Boolean
): Serializable