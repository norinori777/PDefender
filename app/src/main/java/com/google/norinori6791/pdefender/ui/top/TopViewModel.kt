package com.google.norinori6791.pdefender.ui.top

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.norinori6791.pdefender.model.entity.AuthInfo
import com.google.norinori6791.pdefender.model.repository.AuthInfoItem
import com.google.norinori6791.pdefender.model.repository.AuthInfoItems
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class TopViewModel : BaseObservable() {

    val items = ObservableArrayList<AuthInfo>()
    val onShow = MutableLiveData<AuthInfo>()

    fun getAuthInfoItems(){
        Observable.create<String>{
            try {
                it.onNext("登録")
                it.onComplete()
            } catch(e:Exception){
                it.onError(Throwable("エラーになりました"))
            }
        }
            .subscribeOn(Schedulers.newThread())
            .doOnNext{
                val addInstance = AuthInfoItems()
                addInstance.getItems()
            }
            .doOnError {
                Log.v("null", "onError")
            }
            .doOnComplete {
                afterAddAuthInfo()
            }
            .subscribe({}, {})
    }

    private fun afterAddAuthInfo(){

    }
}